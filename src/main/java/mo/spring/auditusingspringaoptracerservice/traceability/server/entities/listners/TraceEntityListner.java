package mo.spring.auditusingspringaoptracerservice.traceability.server.entities.listners;

import mo.spring.auditusingspringaoptracerservice.traceability.server.changes.IChangesAnalyzer;
import mo.spring.auditusingspringaoptracerservice.traceability.server.constants.TraceActions;
import mo.spring.auditusingspringaoptracerservice.traceability.server.entities.Trace;
import mo.spring.auditusingspringaoptracerservice.repositories.TraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public class TraceEntityListner {

    @Autowired
    @Lazy
    private TraceRepository traceRepository;

    @Autowired
    @Lazy
    private IChangesAnalyzer changesAnalyzer;

    @PrePersist
    public void prePersist(Trace entity) {
        Optional<Trace> preStateOptional = traceRepository
                .findTop1ByEntityIdAndEntityClassNameOrderByIdTraceDesc(entity.getEntityId(), entity.getEntityClassName());
        preStateOptional.ifPresent(entity::setPreviousTrace);
    }

    @PostPersist
    public void postPersist(Trace entity) {
        if(entity.getAction().equals(TraceActions.UPDATE)){
            Optional<Trace> previousTraceOptional = Optional.ofNullable(entity.getPreviousTrace());

            if(previousTraceOptional.isPresent()){
                Object oldState = previousTraceOptional.get().getEntityState();
                Object newState = entity.getEntityState();

                LinkedHashMap<String, String> o = changesAnalyzer.objectToMap(oldState);
                LinkedHashMap<String, String> n = changesAnalyzer.objectToMap(newState);

//                String changes = changesAnalyzer.compare(oldState, newState);
                List<String> changedFieldNames = changesAnalyzer.compareAndGetChangedFieldNames(o, n);
//                String changes = changesAnalyzer.compareAndGetChangedFieldNamesPrettyPrint(o, n);
//                String changes = changesAnalyzer.compareAndGetChangedFieldNamesPrettyPrint(o, n, "- ");

                entity.setChanges(changedFieldNames);
                traceRepository.save(entity);
            }

        }
    }
}
