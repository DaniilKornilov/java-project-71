package hexlet.code.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

final class JsonParser extends ObjectMapperParser {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    protected ObjectMapper getMapper() {
        return MAPPER;
    }
}
