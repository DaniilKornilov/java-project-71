package hexlet.code;

import hexlet.code.parser.JsonParser;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public final class Differ {

    private static final JsonParser JSON_PARSER = new JsonParser();

    public static String generate(String firstFilePath, String secondFilePath, String format) throws IOException {
        Map<String, Object> first = JSON_PARSER.parse(firstFilePath);
        Map<String, Object> second = JSON_PARSER.parse(secondFilePath);

        Set<String> keys = new TreeSet<>();
        keys.addAll(first.keySet());
        keys.addAll(second.keySet());

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (String key : keys) {
            boolean inFirst = first.containsKey(key);
            boolean inSecond = second.containsKey(key);
            Object v1 = first.get(key);
            Object v2 = second.get(key);

            if (inFirst && inSecond) {
                if (Objects.equals(v1, v2)) {
                    sb.append("   ").append(key).append(": ").append(valueToString(v1)).append("\n");
                } else {
                    sb.append(" - ").append(key).append(": ").append(valueToString(v1)).append("\n");
                    sb.append(" + ").append(key).append(": ").append(valueToString(v2)).append("\n");
                }
            } else if (inFirst) {
                sb.append(" - ").append(key).append(": ").append(valueToString(v1)).append("\n");
            } else {
                sb.append(" + ").append(key).append(": ").append(valueToString(v2)).append("\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private static String valueToString(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return (String) value;
        }
        if (value instanceof Number || value instanceof Boolean) {
            return value.toString();
        }

        throw new IllegalArgumentException("Type expected to be flat! But received:" + value.getClass());
    }
}
