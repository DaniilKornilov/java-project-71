package hexlet.code.formatter;

import hexlet.code.diff.DiffEntry;

import java.util.List;

final class StylishFormatter implements Formatter {

    @Override
    public String format(List<DiffEntry> diffEntries) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");

        for (DiffEntry entry : diffEntries) {
            switch (entry.type()) {
                case UNCHANGED -> sb.append("   ")
                        .append(entry.key()).append(": ")
                        .append(stringify(entry.oldValue()));
                case REMOVED -> sb.append(" - ")
                        .append(entry.key()).append(": ")
                        .append(stringify(entry.oldValue()));
                case ADDED -> sb.append(" + ")
                        .append(entry.key()).append(": ")
                        .append(stringify(entry.newValue()));
                case UPDATED -> sb.append(" - ")
                        .append(entry.key()).append(": ")
                        .append(stringify(entry.oldValue())).append("\n")
                        .append(" + ")
                        .append(entry.key()).append(": ")
                        .append(stringify(entry.newValue()));
            }
            sb.append("\n");
        }

        sb.append("}");
        return sb.toString();
    }

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }
        return value.toString();
    }
}
