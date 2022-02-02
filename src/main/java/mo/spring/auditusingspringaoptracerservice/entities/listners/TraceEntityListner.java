package mo.spring.auditusingspringaoptracerservice.entities.listners;

import mo.spring.auditusingspringaoptracerservice.entities.Trace;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class TraceEntityListner {
    @PostPersist
    public void postPersist(Trace entity) {

    }

    @PostUpdate
    public void postUpdate(Trace entity){

    }
}
