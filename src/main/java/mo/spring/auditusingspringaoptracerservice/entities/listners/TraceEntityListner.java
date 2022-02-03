package mo.spring.auditusingspringaoptracerservice.entities.listners;

import mo.spring.auditusingspringaoptracerservice.entities.Trace;
import mo.spring.auditusingspringaoptracerservice.repositories.TraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
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
        if(entity.getAction() == "UPDATE"){
            // compare the ennity.getEntityState() and the preTrace.getEntityState()
        }
    }
}
