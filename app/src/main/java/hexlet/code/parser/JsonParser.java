package hexlet.code.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

final class JsonParser extends Parser {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    protected ObjectMapper getMapper() {
        return MAPPER;
    }
}
