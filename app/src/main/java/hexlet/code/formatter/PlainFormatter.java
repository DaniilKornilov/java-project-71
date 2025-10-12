package hexlet.code.formatter;

import hexlet.code.diff.DiffEntry;

import java.util.List;

final class PlainFormatter implements Formatter {
    @Override
    public String format(List<DiffEntry> diffEntries) {
        StringBuilder sb = new StringBuilder();

        for (DiffEntry entry : diffEntries) {
            String key = entry.key();
            switch (entry.type()) {
                case UNCHANGED -> {
                    // skip unchanged
                }
                case REMOVED -> sb.append("Property '")
                        .append(key)
                        .append("' was removed\n");
                case ADDED -> sb.append("Property '")
                        .append(key)
                        .append("' was added with value: ")
                        .append(stringify(entry.newValue()))
                        .append("\n");
                case CHANGED -> sb.append("Property '")
                        .append(key)
                        .append("' was updated. From ")
                        .append(stringify(entry.oldValue()))
                        .append(" to ")
                        .append(stringify(entry.newValue()))
                        .append("\n");
            }
        }

        return sb.toString();
    }

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String s) {
            return "'" + s + "'";
        }
        if (value instanceof Number || value instanceof Boolean) {
            return value.toString();
        }
        return "[complex value]";
    }
}
