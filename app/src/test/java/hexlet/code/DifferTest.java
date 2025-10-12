package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class DifferTest {

    @Test
    void testUnknownFormat() {
        assertThrows(IllegalArgumentException.class, () -> Differ.generate(
                "src/test/resources/file1.json",
                "src/test/resources/file2.json",
                "unknown"
        ));
    }

    @Test
    void testUnknownExtension() {
        assertThrows(IllegalArgumentException.class, () -> Differ.generate(
                "src/test/resources/wrong-ext.txt",
                "src/test/resources/file2.json",
                "json"
        ));
    }

    @Test
    void testNoExtension() {
        assertThrows(IllegalArgumentException.class, () -> Differ.generate(
                "src/test/resources/no-ext",
                "src/test/resources/file2.json",
                "json"
        ));
    }

    @ParameterizedTest
    @MethodSource("diffCases")
    void testDiffGeneration(TestCase testCase) throws IOException {
        String expected = readResource(testCase.expectedPath());
        String actual = Differ.generate(
                "src/test/resources/" + testCase.firstFile(),
                "src/test/resources/" + testCase.secondFile(),
                testCase.format()
        );

        assertEquals(expected, actual);
    }

    private static Stream<TestCase> diffCases() {
        return Stream.of(
                new TestCase("file1.json", "file2.json", "stylish", "stylish.txt"),
                new TestCase("file1.json", "file2.json", "plain", "plain.txt"),
                new TestCase("file1.json", "file2.json", "json", "json.txt"),
                new TestCase("file1.yml", "file2.yml", "stylish", "stylish.txt"),
                new TestCase("file1.yml", "file2.yml", "plain", "plain.txt"),
                new TestCase("file1.yml", "file2.yml", "json", "json.txt")
        );
    }

    private static String readResource(String resourceName) throws IOException {
        Path path = Path.of("src", "test", "resources", resourceName);
        return Files.readString(path).trim();
    }

    private record TestCase(String firstFile, String secondFile, String format, String expectedPath) {
    }
}
