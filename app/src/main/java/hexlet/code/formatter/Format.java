package hexlet.code.formatter;

import java.util.HashMap;
import java.util.Map;

public enum Format {
    STYLISH("stylish"),
    PLAIN("plain"),
    JSON("json");

    private static final Map<String, Format> LOOKUP = new HashMap<>();

    static {
        for (Format format : values()) {
            LOOKUP.put(format.name, format);
        }
    }

    private final String name;

    Format(String formatName) {
        this.name = formatName;
    }

    static Format fromFormat(String format) {
        if (format == null) {
            throw new IllegalArgumentException("Formatter type cannot be null");
        }
        return LOOKUP.get(format.toLowerCase());
    }
}
