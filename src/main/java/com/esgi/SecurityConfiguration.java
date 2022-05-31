package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.modules.security.application.EncryptPasswordCommand;
import com.esgi.modules.security.application.EncryptPasswordCommandHandler;
import com.esgi.modules.security.domain.EncryptionService;
import com.esgi.modules.security.infrastructure.GuavaEncryptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfiguration {
    private final KernelConfiguration kernelConfiguration;

    public SecurityConfiguration(KernelConfiguration kernelConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
    }

    @Bean
    public EncryptionService encryptionService() {
        return new GuavaEncryptionService();
    }

    @Bean
    public CommandBus securityCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(EncryptPasswordCommand.class, new EncryptPasswordCommandHandler(encryptionService()));
        return commandBus;
    }
}
