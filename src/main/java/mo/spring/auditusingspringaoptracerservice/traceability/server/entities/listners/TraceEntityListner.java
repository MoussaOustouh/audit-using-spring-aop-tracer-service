package mo.spring.auditusingspringaoptracerservice.traceability.server.entities.listners;

import mo.spring.auditusingspringaoptracerservice.traceability.server.constants.TraceActions;
import mo.spring.auditusingspringaoptracerservice.traceability.server.entities.Trace;
import mo.spring.auditusingspringaoptracerservice.repositories.TraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import java.util.Optional;

public class TraceEntityListner {

    @Autowired
    @Lazy
    private TraceRepository traceRepository;

    @PrePersist
    public void prePersist(Trace entity) {
        Optional<Trace> preStateOptional = traceRepository
                .findTop1ByEntityIdAndEntityClassNameOrderByIdTraceDesc(entity.getEntityId(), entity.getEntityClassName());
        preStateOptional.ifPresent(entity::setPreviousTrace);
    }

    @PostPersist
    public void postPersist(Trace entity) {
        System.out.println("Trace.action : " + entity.getAction());
        if(entity.getAction().equals(TraceActions.UPDATE)){
            // compare the entity.getEntityState() and entity.getPreviousTrace().getEntityState()
            System.out.println("compare the entity.getEntityState() and entity.getPreviousTrace().getEntityState()");
            // then save the changes in entity.changes
        }
    }
}
