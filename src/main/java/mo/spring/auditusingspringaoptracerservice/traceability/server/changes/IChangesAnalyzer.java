package mo.spring.auditusingspringaoptracerservice.traceability.server.changes;

import java.util.LinkedHashMap;
import java.util.List;

public interface IChangesAnalyzer {
//    String compare(Object oldState, Object newState);
//    String compareLinkedHashMap(LinkedHashMap<String, String> oldState, LinkedHashMap<String, String> newState);

    List<String> compareAndGetChangedFieldNames(LinkedHashMap<String, String> oldState, LinkedHashMap<String, String> newState);
    String getChangedFieldNamesPrettyPrint(List<String> changedFieldNames, String concat);
    String compareAndGetChangedFieldNamesPrettyPrint(LinkedHashMap<String, String> oldState, LinkedHashMap<String, String> newState, String concat);

    LinkedHashMap<String, String> objectToMap(Object object);
}
