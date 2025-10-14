package hexlet.code.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

abstract sealed class ObjectMapperParser implements Parser
        permits JsonParser, YamlParser {

    @Override
    public Map<String, Object> parse(String content) throws IOException {
        return getMapper().readValue(content, new TypeReference<>() {
        });
    }

    protected abstract ObjectMapper getMapper();
}
