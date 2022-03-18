package com.esgi.modules.file.application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileService {
    public void createFile(String filePath, String content) {
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
            throw new FileCreationException(filePath);
        }
    }
}
