package com.esgi.modules.code.domain;

public class Script {
    private static final String SCRIPT_PREFIX = "timeout 10s ";
    private final String command;

    private Script(String command) {
        this.command = command;
    }

    public static Script of(String command) {
        return new Script(SCRIPT_PREFIX + command);
    }

    public String getCommand() {
        return command;
    }
}
