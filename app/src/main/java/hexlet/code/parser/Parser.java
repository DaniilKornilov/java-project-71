package hexlet.code.parser;

import java.io.IOException;
import java.util.Map;

public interface Parser {
    Map<String, Object> parse(String filePath) throws IOException;
}
