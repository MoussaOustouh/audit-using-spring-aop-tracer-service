package mo.spring.auditusingspringaoptracerservice.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TraceDTOHelper implements Serializable {
    private static final long serialVersionUID = -3435247283237432197L;

    Long idTrace;

    Long userId;
    String ipAddress;
    Object entityState;
    String entityClassName;
    Long entityId;

    String action;
    String actionInfo;
    String changes;
    LocalDateTime tracedAt;

    public TraceDTOHelper() {
    }

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
        return "TraceDTO{" +
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