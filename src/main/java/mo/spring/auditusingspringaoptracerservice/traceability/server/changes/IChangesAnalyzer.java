package mo.spring.auditusingspringaoptracerservice.traceability.server.changes;

import java.util.LinkedHashMap;
import java.util.List;

public interface IChangesAnalyzer {
    List<String> compareObjectsAndGetChangedFieldNames(Object oldState, Object newState);
    List<String> compareAndGetChangedFieldNames(LinkedHashMap<String, String> oldState, LinkedHashMap<String, String> newState);
    String getChangedFieldNamesPrettyPrint(List<String> changedFieldNames, String concat);

    LinkedHashMap<String, String> objectToMap(Object object);
}
