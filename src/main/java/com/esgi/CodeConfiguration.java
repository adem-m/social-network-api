package com.esgi;

import com.esgi.modules.code.application.CodeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CodeConfiguration {
    private final FileConfiguration fileConfiguration;

    public CodeConfiguration(FileConfiguration fileConfiguration) {
        this.fileConfiguration = fileConfiguration;
    }

    @Bean
    public CodeService codeService() {
        return new CodeService(fileConfiguration.fileService());
    }
}
