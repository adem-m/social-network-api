package com.esgi.modules.code.application;

import com.esgi.modules.code.domain.*;
import com.esgi.modules.file.application.FileService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

final public class CodeService {
    private final static int SUCCESS_CODE = 0;
    private final static String SCRIPTS_DIRECTORY = "/home/ec2-user/";
    private final static Map<Language, Script> LANGUAGE_SCRIPT = Map.of(
            Language.C, new Script(SCRIPTS_DIRECTORY + Language.C.getScriptName()),
            Language.PYTHON, new Script(SCRIPTS_DIRECTORY + Language.PYTHON.getScriptName())
    );

    private final FileService fileService;

    public CodeService(FileService fileService) {
        this.fileService = fileService;
    }

    public Output execute(Code code) {
        Script script = LANGUAGE_SCRIPT.get(code.language());
        fileService.createFile(code.language().getSourceName(), code.source());
        return runScript(script);
    }

    private Output runScript(Script script) {
        Duration duration = null;
        final long startTime = System.nanoTime();
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(script.command());
        StringBuilder output = new StringBuilder();
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            int exitCode = process.waitFor();
            final long endTime = System.nanoTime();
            duration = new Duration((endTime - startTime) / 1_000_000);
            if (exitCode != SUCCESS_CODE) {
                return Output.fail("Error while running script, exit code :" + exitCode, duration);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Output.success(output.toString(), duration);
    }
}
