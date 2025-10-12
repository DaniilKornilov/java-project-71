package hexlet.code.parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
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

    FileExtension(String... aliases) {
        this.aliases = new HashSet<>();
        for (String alias : aliases) {
            this.aliases.add(alias.toLowerCase(Locale.ROOT));
        }
    }

    static FileExtension fromExtension(String extension) {
        if (extension == null) {
            return JSON;
        }
        return LOOKUP.get(extension);
    }
}
