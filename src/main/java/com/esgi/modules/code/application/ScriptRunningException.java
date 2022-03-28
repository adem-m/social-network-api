package com.esgi.modules.code.application;

import com.esgi.modules.code.domain.Script;

public class ScriptRunningException extends UnsupportedOperationException {
    public ScriptRunningException(Script script) {
        super("An error occurred while running script : " + script.command());
    }
}
