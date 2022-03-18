package com.esgi.modules.file.application;

public class FileCreationException extends IllegalStateException {
    public FileCreationException(String fileName) {
        super("Unable to create file " + fileName);
    }
}
