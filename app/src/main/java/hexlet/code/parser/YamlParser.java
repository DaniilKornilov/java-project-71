package hexlet.code.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

final class YamlParser extends Parser {
    private static final ObjectMapper MAPPER = new ObjectMapper(new YAMLFactory());

    @Override
    protected ObjectMapper getMapper() {
        return MAPPER;
    }
}
