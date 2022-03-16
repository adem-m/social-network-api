package com.esgi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;

@RestController
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<String> test() throws IOException {
        String pythonScript = "print('Hello')\nprint('World')";
        final long startTime = System.nanoTime();

        ProcessBuilder processBuilder = new ProcessBuilder();

        final String FILE_NAME = "/home/ec2-user/python_src/test.py";
        File targetFile = new File(FILE_NAME);
        targetFile.delete();
        targetFile.createNewFile();

        FileOutputStream outputStream = new FileOutputStream(targetFile);
        outputStream.write(pythonScript.getBytes(StandardCharsets.UTF_8));
        outputStream.close();


        // -- Linux --

        // Run a shell command
//        processBuilder.command("bash", "-c", "ls /home/mkyong/");

//        try {
//            new ProcessBuilder().command("echo \"" + pythonScript + "\" > /home/ec2-user/python_src/test.py").start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // Run a shell script
        processBuilder.command("/home/ec2-user/run_python.sh");

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
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.
        return ResponseEntity.ok(output + " " + duration + "ms");
    }
}
