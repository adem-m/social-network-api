package com.esgi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        String pythonScript = "print('Hello')\nprint('World')";
        ProcessBuilder processBuilder = new ProcessBuilder();

        // -- Linux --

        // Run a shell command
//        processBuilder.command("bash", "-c", "ls /home/mkyong/");

        // Run a shell script
        processBuilder.command(
                "echo \"" + pythonScript + "\" > /home/ec2-user/python_src/test.py",
                "/home/ec2-user/run_python.sh"
        );

        // -- Windows --

        // Run a command
//        processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\ademr");

        // Run a bat file
        //processBuilder.command("C:\\Users\\mkyong\\hello.bat");
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
                return ResponseEntity.internalServerError().body("Cannot run python script.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(output.toString());
    }
}
