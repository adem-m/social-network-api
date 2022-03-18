package com.esgi;

import com.esgi.modules.file.application.FileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileConfiguration {
    @Bean
    public FileService fileService() {
        return new FileService();
    }
}
