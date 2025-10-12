package hexlet.code.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public sealed abstract class Parser permits JsonParser, YamlParser {

    protected abstract ObjectMapper getMapper();

    public Map<String, Object> parse(String filePath) throws IOException {
        Path path = normalizePath(filePath);

        try (InputStream in = Files.newInputStream(path)) {
            return getMapper().readValue(in, new TypeReference<>() {
            });
        }
    }

    private Path normalizePath(String path) throws IOException {
        Path absolutePath = Paths.get(path).toAbsolutePath().normalize();
        if (!Files.exists(absolutePath)) {
            throw new NoSuchFileException("File not found: " + absolutePath);
        }
        return absolutePath;
    }
}
