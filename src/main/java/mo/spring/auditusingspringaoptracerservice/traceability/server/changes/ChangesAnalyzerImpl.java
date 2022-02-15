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

    @Override
    @SuppressWarnings("unchecked")
    public List<String> compareObjectsAndGetChangedFieldNames(Object oldState, Object newState) {
        LinkedHashMap<String, String> oldS = this.objectToMap(oldState);
        LinkedHashMap<String, String> newS = this.objectToMap(newState);

        return this.compareAndGetChangedFieldNames(oldS, newS);
    }

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
        List<String> list = changedFieldNames.stream()
                .map(cfn->concat+cfn)
                .collect(Collectors.toList());

        return String.join("\n", list);
    }

    @Override
    public LinkedHashMap<String, String> objectToMap(Object object) {
        return oMapper.convertValue(object, LinkedHashMap.class);
    }
}