package hexlet.code.parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

enum FileExtension {
    JSON("json"),
    YAML("yaml", "yml");

    private static final Map<String, FileExtension> LOOKUP = new HashMap<>();

    static {
        for (FileExtension extension : values()) {
            for (String alias : extension.aliases) {
                LOOKUP.put(alias, extension);
            }
        }
    }

    private final Set<String> aliases;

    FileExtension(String... extensionAliases) {
        this.aliases = new HashSet<>();
        for (String alias : extensionAliases) {
            this.aliases.add(alias.toLowerCase());
        }
    }

    static FileExtension fromExtension(String extension) {
        if (extension == null) {
            throw new IllegalArgumentException("File extension cannot be null");
        }
        return LOOKUP.get(extension);
    }
}
