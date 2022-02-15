package mo.spring.auditusingspringaoptracerservice.traceability.server.entities.listners;

import mo.spring.auditusingspringaoptracerservice.traceability.server.changes.IChangesAnalyzer;
import mo.spring.auditusingspringaoptracerservice.traceability.server.constants.TraceActions;
import mo.spring.auditusingspringaoptracerservice.traceability.server.entities.Trace;
import mo.spring.auditusingspringaoptracerservice.repositories.TraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
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
                List<String> changedFieldNames = changesAnalyzer.compareObjectsAndGetChangedFieldNames(oldState, newState);;

                entity.setChanges(changedFieldNames);
                traceRepository.save(entity);
            }

        }
    }
}
