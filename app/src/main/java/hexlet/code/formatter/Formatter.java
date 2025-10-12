package hexlet.code.formatter;

import hexlet.code.diff.DiffEntry;

import java.io.IOException;
import java.util.List;

public interface Formatter {
    String format(List<DiffEntry> diffEntries) throws IOException;
}
