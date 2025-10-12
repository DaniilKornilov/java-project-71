package hexlet.code;

import hexlet.code.diff.DiffEntry;
import hexlet.code.diff.DiffType;
import hexlet.code.formatter.StylishFormatter;
import hexlet.code.parser.Parser;
import hexlet.code.parser.ParserFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class Differ {
    private Differ() {
    }

    public static String generate(String firstFilePath, String secondFilePath, String format) throws IOException {
        Map<String, Object> first = getFileData(firstFilePath);
        Map<String, Object> second = getFileData(secondFilePath);

        List<DiffEntry> diff = diff(first, second);
        return StylishFormatter.format(diff);
    }

    private static Map<String, Object> getFileData(String filePath) throws IOException {
        Parser parser = ParserFactory.getParser(filePath);
        return parser.parse(filePath);
    }

    private static List<DiffEntry> diff(Map<String, Object> first, Map<String, Object> second) {
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
                    result.add(new DiffEntry(DiffType.REMOVED, key, v1, null));
                    result.add(new DiffEntry(DiffType.ADDED, key, null, v2));
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
