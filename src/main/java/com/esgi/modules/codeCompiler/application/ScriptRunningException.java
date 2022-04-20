package com.esgi.modules.codeCompiler.application;

import com.esgi.modules.codeCompiler.domain.Script;

public class ScriptRunningException extends UnsupportedOperationException {
    public ScriptRunningException(Script script) {
        super("An error occurred while running script : " + script.command());
    }
}
