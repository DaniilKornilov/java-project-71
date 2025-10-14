package hexlet.code;

import hexlet.code.diff.DiffCalculator;
import hexlet.code.diff.DiffEntry;
import hexlet.code.file.FileExtension;
import hexlet.code.file.FileReader;
import hexlet.code.formatter.Format;
import hexlet.code.formatter.Formatter;
import hexlet.code.formatter.FormatterFactory;
import hexlet.code.parser.Parser;
import hexlet.code.parser.ParserFactory;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class Differ {
    private Differ() {
    }

    public static String generate(String firstFilePath, String secondFilePath) throws IOException {
        return generate(firstFilePath, secondFilePath, Format.STYLISH.getName());
    }

    public static String generate(String firstFilePath, String secondFilePath, String format) throws IOException {
        Map<String, Object> first = parseFileData(firstFilePath);
        Map<String, Object> second = parseFileData(secondFilePath);

        List<DiffEntry> diff = DiffCalculator.calculateDiff(first, second);
        return formatData(format, diff);
    }

    private static Map<String, Object> parseFileData(String filePath) throws IOException {
        FileExtension fileExtension = FileExtension.fromExtension(FilenameUtils.getExtension(filePath));
        Parser parser = ParserFactory.getParser(fileExtension);
        String content = FileReader.readFile(filePath);
        return parser.parse(content);
    }

    private static String formatData(String format, List<DiffEntry> diffEntries) throws IOException {
        Formatter formatter = FormatterFactory.getFormatter(format);
        return formatter.format(diffEntries);
    }
}
