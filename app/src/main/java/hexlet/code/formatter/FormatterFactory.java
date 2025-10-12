package hexlet.code.formatter;

import java.util.EnumMap;
import java.util.Map;

public final class FormatterFactory {
    private FormatterFactory() {
    }

    private static final Map<Format, Formatter> FORMATTERS = new EnumMap<>(Format.class);

    static {
        FORMATTERS.put(Format.STYLISH, new StylishFormatter());
        FORMATTERS.put(Format.PLAIN, new PlainFormatter());
        FORMATTERS.put(Format.JSON, new JsonFormatter());
    }

    public static Formatter getFormatter(String format) {
        Formatter formatter = FORMATTERS.get(Format.fromFormat(format));
        if (formatter == null) {
            throw new IllegalArgumentException("Unknown formatter: " + format);
        }
        return formatter;
    }
}
