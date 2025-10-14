package hexlet.code.file;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileReader {
    private FileReader() {
    }

    public static String readFile(String filePath) throws IOException {
        Path path = normalizePath(filePath);
        try (InputStream in = Files.newInputStream(path)) {
            return new String(in.readAllBytes());
        }
    }

    private static Path normalizePath(String filePath) throws IOException {
        Path absolutePath = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(absolutePath)) {
            throw new NoSuchFileException("File not found: " + absolutePath);
        }
        return absolutePath;
    }
}
