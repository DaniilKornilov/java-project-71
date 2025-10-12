package hexlet.code.parser;

import org.apache.commons.io.FilenameUtils;

import java.util.HashMap;
import java.util.Map;

public final class ParserFactory {
    private ParserFactory() {
    }

    private static final Map<FileExtension, Parser> PARSERS = new HashMap<>();

    static {
        PARSERS.put(FileExtension.YAML, new YamlParser());
        PARSERS.put(FileExtension.JSON, new JsonParser());
    }

    public static Parser getParser(String filePath) {
        String fileExtension = FilenameUtils.getExtension(filePath);
        return PARSERS.get(FileExtension.fromExtension(fileExtension));
    }
}
