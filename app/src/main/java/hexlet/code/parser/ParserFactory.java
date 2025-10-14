package hexlet.code.parser;

import hexlet.code.file.FileExtension;

import java.util.EnumMap;
import java.util.Map;

public final class ParserFactory {
    private ParserFactory() {
    }

    private static final Map<FileExtension, Parser> PARSERS = new EnumMap<>(FileExtension.class);

    static {
        PARSERS.put(FileExtension.YAML, new YamlParser());
        PARSERS.put(FileExtension.JSON, new JsonParser());
    }

    public static Parser getParser(FileExtension fileExtension) {
        Parser parser = PARSERS.get(fileExtension);
        if (parser == null) {
            throw new IllegalArgumentException("Unknown file extension: " + fileExtension);
        }
        return parser;
    }
}
