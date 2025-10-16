package hexlet.code.formatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.diff.DiffEntry;

import java.io.IOException;
import java.util.List;

final class JsonFormatter implements Formatter {
    @Override
    public String format(List<DiffEntry> diffEntries) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diffEntries);
    }
}
