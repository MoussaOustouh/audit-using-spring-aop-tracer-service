package mo.spring.auditusingspringaoptracerservice.traceability.server.changes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.map.EntryValueChange;
import org.javers.core.diff.changetype.map.MapChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChangesAnalyzerImpl implements IChangesAnalyzer{

    @Autowired
    private Javers javers;

    @Autowired
    private ObjectMapper oMapper;

//    @Override
//    public String compare(Object oldState, Object newState) {
////        LinkedHashMap<String, String> o = objectToMap(oldState);
////        LinkedHashMap<String, String> n = objectToMap(newState);
//
//        Diff diff = javers.compare(oldState, newState);
//
//        return diff.prettyPrint();
//    }

//    @Override
//    public String compareLinkedHashMap(LinkedHashMap<String, String> oldState, LinkedHashMap<String, String> newState) {
//        Diff diff = javers.compare(oldState, newState);
//
//        return diff.prettyPrint();
//    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> compareAndGetChangedFieldNames(LinkedHashMap<String, String> oldState, LinkedHashMap<String, String> newState) {
        List<String> changedFields = new ArrayList<String>();

        Diff diff = javers.compare(oldState, newState);

        Changes changes = diff.getChanges();
        changes.forEach(change -> {
            MapChange mc = (MapChange) change;
            mc.getEntryChanges().forEach(ec ->{
                EntryValueChange evc = (EntryValueChange) ec;
                changedFields.add((String) evc.getKey());
            });
        });

        return changedFields;
    }

    @Override
    public String getChangedFieldNamesPrettyPrint(List<String> changedFieldNames, String concat) {
//        StringBuilder b = new StringBuilder();
        List<String> list = changedFieldNames.stream()
                .map(cfn->concat+cfn)
                .collect(Collectors.toList());

        return String.join("\n", list);
    }

    @Override
    public String compareAndGetChangedFieldNamesPrettyPrint(LinkedHashMap<String, String> oldState, LinkedHashMap<String, String> newState, String concat) {
        List<String> changedFieldNames = this.compareAndGetChangedFieldNames(oldState, newState);
        return this.getChangedFieldNamesPrettyPrint(changedFieldNames, concat);
    }

    @Override
    public LinkedHashMap<String, String> objectToMap(Object object) {
        return oMapper.convertValue(object, LinkedHashMap.class);
    }
}