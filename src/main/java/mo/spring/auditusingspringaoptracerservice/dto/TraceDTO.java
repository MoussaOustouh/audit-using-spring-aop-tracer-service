package mo.spring.auditusingspringaoptracerservice.dto;

import java.io.Serializable;

public class TraceDTO extends TraceDTOHelper implements Serializable {
    private static final long serialVersionUID = 1603237803269868625L;

    private TraceDTOHelper previousTrace;

    public TraceDTO() {
    }

    public TraceDTOHelper getPreviousTrace() {
        return previousTrace;
    }

    public void setPreviousTrace(TraceDTOHelper previousTrace) {
        this.previousTrace = previousTrace;
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