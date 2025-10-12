package hexlet.code.diff;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

final class DiffCalculator {
    private DiffCalculator() {
    }

    public static List<DiffEntry> calculateDiff(Map<String, Object> first, Map<String, Object> second) {
        Set<String> keys = new TreeSet<>();
        keys.addAll(first.keySet());
        keys.addAll(second.keySet());

        List<DiffEntry> result = new ArrayList<>();
        for (String key : keys) {
            boolean inFirst = first.containsKey(key);
            boolean inSecond = second.containsKey(key);
            Object v1 = first.get(key);
            Object v2 = second.get(key);

            if (inFirst && inSecond) {
                if (Objects.equals(v1, v2)) {
                    result.add(new DiffEntry(DiffType.UNCHANGED, key, v1, v2));
                } else {
                    result.add(new DiffEntry(DiffType.CHANGED, key, v1, v2));
                }
            } else if (inFirst) {
                result.add(new DiffEntry(DiffType.REMOVED, key, v1, null));
            } else {
                result.add(new DiffEntry(DiffType.ADDED, key, null, v2));
            }
        }
        return result;
    }
}
