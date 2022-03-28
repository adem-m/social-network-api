package com.esgi.modules.code.domain;

import com.esgi.modules.code.application.UnsupportedLanguageException;

public enum Language {
    C("run_c.sh", "c_src/app.c", 124),
    PYTHON("run_python.sh", "python_src/app.py", 143);

    private final String scriptName;
    private final String sourceName;
    private final int timeoutCode;

    Language(String scriptName, String sourceName, int timeoutCode) {
        this.scriptName = scriptName;
        this.sourceName = sourceName;
        this.timeoutCode = timeoutCode;
    }

    public String getScriptName() {
        return scriptName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public int getTimeoutCode() {
        return timeoutCode;
    }

    public static Language fromString(String language) {
        return switch (language) {
            case "c" -> C;
            case "python" -> PYTHON;
            default -> throw new UnsupportedLanguageException(language);
        };
    }
}
