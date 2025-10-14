package hexlet.code.formatter;

import hexlet.code.diff.DiffEntry;

import java.util.List;
import java.util.Map;

final class PlainFormatter implements Formatter {
    private static final String PROPERTY = "Property '";

    @Override
    public String format(List<DiffEntry> diffEntries) {
        StringBuilder sb = new StringBuilder();

        for (DiffEntry entry : diffEntries) {
            String key = entry.key();
            switch (entry.type()) {
                case UNCHANGED -> {
                    // skip unchanged
                }
                case REMOVED -> sb.append(PROPERTY)
                        .append(key)
                        .append("' was removed\n");
                case ADDED -> sb.append(PROPERTY)
                        .append(key)
                        .append("' was added with value: ")
                        .append(stringify(entry.newValue()))
                        .append("\n");
                case CHANGED -> sb.append(PROPERTY)
                        .append(key)
                        .append("' was updated. From ")
                        .append(stringify(entry.oldValue()))
                        .append(" to ")
                        .append(stringify(entry.newValue()))
                        .append("\n");
                default -> {
                    // skip unknown diff type
                }
            }
        }

        return sb.toString().trim();
    }

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String s) {
            return "'" + s + "'";
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        return value.toString();
    }
}
