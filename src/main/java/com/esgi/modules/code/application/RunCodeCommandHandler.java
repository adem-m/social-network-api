package com.esgi.modules.code.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.code.domain.Duration;
import com.esgi.modules.code.domain.Language;
import com.esgi.modules.code.domain.Output;
import com.esgi.modules.code.domain.Script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

final public class RunCodeCommandHandler implements CommandHandler<RunCode, Output> {
    private final static int SUCCESS_CODE = 0;
    private final static String SCRIPTS_DIRECTORY = "/home/ec2-user/";
    private final static Map<Language, Script> LANGUAGE_SCRIPT = Map.of(
            Language.C, new Script(SCRIPTS_DIRECTORY + Language.C.getScriptName()),
            Language.PYTHON, new Script(SCRIPTS_DIRECTORY + Language.PYTHON.getScriptName())
    );

    private final EventDispatcher<Event> eventEventDispatcher;

    public RunCodeCommandHandler(EventDispatcher<Event> eventEventDispatcher) {
        this.eventEventDispatcher = eventEventDispatcher;
    }


    @Override
    public Output handle(RunCode command) {
        Script script = LANGUAGE_SCRIPT.get(command.code().language());
        Duration duration;
        final long startTime = System.nanoTime();
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(script.command());
        StringBuilder output = new StringBuilder();
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            while ((line = errorReader.readLine()) != null) {
                output.append(line).append("\n");
            }
            int exitCode = process.waitFor();
            final long endTime = System.nanoTime();
            duration = new Duration((endTime - startTime) / 1_000_000);
            if (exitCode == command.code().language().getTimeoutCode()) {
                return Output.timeout();
            }
            if (exitCode != SUCCESS_CODE) {
                return Output.fail(output.toString(), duration);
            }

        } catch (IOException | InterruptedException e) {
            throw new ScriptRunningException(script);
        }
        this.eventEventDispatcher.dispatch(new RunCodeEvent(command.code()));
        return Output.success(output.toString(), duration);
    }
}