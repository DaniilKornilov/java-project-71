package hexlet.code.parser;

import java.util.EnumMap;
import java.util.Map;

public final class ParserFactory {
    private ParserFactory() {
    }

    private static final Map<DataType, Parser> PARSERS = new EnumMap<>(DataType.class);

    static {
        PARSERS.put(DataType.YAML, new YamlParser());
        PARSERS.put(DataType.JSON, new JsonParser());
    }

    public static Parser getParser(DataType dataType) {
        Parser parser = PARSERS.get(dataType);
        if (parser == null) {
            throw new IllegalArgumentException("Unknown data type: " + dataType);
        }
        return parser;
    }
}
