package com.esgi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;

@RestController
public class TestController {
    final private String FILE_PATH = "/home/ec2-user/python_src/test.py";

    @GetMapping("/test")
    public final ResponseEntity<String> test() {
        final String pythonScript = "print('Hello')\nprint('World')";
        final long startTime = System.nanoTime();

        createFile(FILE_PATH, pythonScript);
        final String output = executeCommand("/home/ec2-user/run_python.sh");

        final long endTime = System.nanoTime();

        final long duration = (endTime - startTime) / 1_000_000;
        return ResponseEntity.ok(output + " " + duration + "ms");
    }

    private void createFile(String filePath, String content) {
        try {
            File targetFile = new File(filePath);
            if (targetFile.exists()) {
                targetFile.delete();
                targetFile.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(targetFile);
            outputStream.write(content.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String executeCommand(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(command);

        StringBuilder output = new StringBuilder();
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitVal = process.waitFor();
            if (exitVal != 0) {
                throw new UnsupportedOperationException("Cannot run script");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
