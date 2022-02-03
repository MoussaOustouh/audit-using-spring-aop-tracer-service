package mo.spring.auditusingspringaoptracerservice.traceability.server.entities;

import mo.spring.auditusingspringaoptracerservice.traceability.server.entities.converters.JpaConverterJson;
import mo.spring.auditusingspringaoptracerservice.traceability.server.entities.listners.TraceEntityListner;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "traces")
@EntityListeners(TraceEntityListner.class)
public class Trace implements Serializable {
    private static final long serialVersionUID = 3124638415250772441L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idTrace;

    private Long userId;
    private String ipAddress;

    @Convert(converter = JpaConverterJson.class)
    @Lob
    private Object entityState;

    private String entityClassName;
    private Long entityId;

    private String action;
    private String actionInfo;

    @Lob
    private String changes;

    private LocalDateTime tracedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_trace_id")
    private Trace previousTrace;

    public Long getIdTrace() {
        return idTrace;
    }

    public void setIdTrace(Long idTrace) {
        this.idTrace = idTrace;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Object getEntityState() {
        return entityState;
    }

    public void setEntityState(Object entityState) {
        this.entityState = entityState;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(String actionInfo) {
        this.actionInfo = actionInfo;
    }

    public Trace getPreviousTrace() {
        return previousTrace;
    }

    public void setPreviousTrace(Trace previousTrace) {
        this.previousTrace = previousTrace;
    }

    public String getChanges() {
        return changes;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }

    public LocalDateTime getTracedAt() {
        return tracedAt;
    }

    public void setTracedAt(LocalDateTime tracedAt) {
        this.tracedAt = tracedAt;
    }

    @Override
    public String toString() {
        return "Trace{" +
                "idTrace=" + idTrace +
                ", userId=" + userId +
                ", ipAddress='" + ipAddress + '\'' +
                ", entityState=" + entityState +
                ", entityClassName='" + entityClassName + '\'' +
                ", entityId=" + entityId +
                ", action='" + action + '\'' +
                ", actionInfo='" + actionInfo + '\'' +
                ", changes='" + changes + '\'' +
                ", tracedAt='" + tracedAt + '\'' +
                '}';
    }
}
