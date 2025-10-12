package hexlet.code.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public final class JsonParser implements Parser {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public Map<String, Object> parse(String filePath) throws IOException {
        return parse(Paths.get(filePath));
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> parse(Path path) throws IOException {
        Path absolutePath = path.toAbsolutePath().normalize();
        if (!Files.exists(absolutePath)) {
            throw new NoSuchFileException("File not found: " + absolutePath);
        }
        Object parsed = MAPPER.readValue(Files.newInputStream(absolutePath), Object.class);

        if (parsed instanceof Map) {
            return (Map<String, Object>) parsed;
        } else {
            return Map.of("root", parsed);
        }
    }
}
