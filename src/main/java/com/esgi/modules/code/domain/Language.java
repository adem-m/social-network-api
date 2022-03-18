package com.esgi.modules.code.domain;

import com.esgi.modules.code.application.UnsupportedLanguageException;

public enum Language {
    C("run_c.sh", "c_src/app.c"),
    PYTHON("run_python.sh", "python_src/app.py");

    private final String scriptName;
    private final String sourceName;

    Language(String scriptName, String sourceName) {
        this.scriptName = scriptName;
        this.sourceName = sourceName;
    }

    public String getScriptName() {
        return scriptName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public static Language fromString(String language) {
        return switch (language) {
            case "c" -> C;
            case "python" -> PYTHON;
            default -> throw new UnsupportedLanguageException(language);
        };
    }
}
