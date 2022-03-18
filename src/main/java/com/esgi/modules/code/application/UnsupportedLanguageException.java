package com.esgi.modules.code.application;

final public class UnsupportedLanguageException extends UnsupportedOperationException {
    public UnsupportedLanguageException(String language) {
        super(language + " is not a supported language.");
    }
}
