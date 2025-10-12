package hexlet.code.formatter;

import hexlet.code.diff.DiffEntry;

import java.util.List;

public class JsonFormatter {

    private JsonFormatter() {
    }

    public static String format(List<DiffEntry> diffEntries) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (DiffEntry entry : diffEntries) {
            switch (entry.type()) {
                case UNCHANGED -> sb.append("   ")
                        .append(entry.key()).append(": ")
                        .append(valueToString(entry.oldValue())).append("\n");
                case REMOVED -> sb.append(" - ")
                        .append(entry.key()).append(": ")
                        .append(valueToString(entry.oldValue())).append("\n");
                case ADDED -> sb.append(" + ")
                        .append(entry.key()).append(": ")
                        .append(valueToString(entry.newValue())).append("\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private static String valueToString(Object value) {
        if (value == null) return "null";
        if (value instanceof String s) return s;
        if (value instanceof Number || value instanceof Boolean) return value.toString();
        return String.valueOf(value);
    }
}
