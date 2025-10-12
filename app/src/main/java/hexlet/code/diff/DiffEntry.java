package hexlet.code.diff;

public record DiffEntry(DiffType type, String key, Object oldValue, Object newValue) {
}
