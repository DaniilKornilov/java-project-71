package hexlet.code.parser;

import hexlet.code.file.FileExtension;

public enum DataType {
    YAML,
    JSON;

    public static DataType fromFileExtension(FileExtension fileExtension) {
        switch (fileExtension) {
            case YAML -> {
                return YAML;
            }
            case JSON -> {
                return JSON;
            }
            default -> throw new IllegalArgumentException("Unsupported file extension: " + fileExtension);
        }
    }
}
