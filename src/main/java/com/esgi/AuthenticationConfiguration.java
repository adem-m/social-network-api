package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.modules.authentication.application.*;
import com.esgi.kernel.TokenService;
import com.esgi.modules.authentication.infrastructure.JWTTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationConfiguration {
    private final KernelConfiguration kernelConfiguration;

    public AuthenticationConfiguration(KernelConfiguration kernelConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
    }

    @Bean
    public TokenService tokenService() {
        return new JWTTokenService();
    }

    @Bean
    public LoginCommandHandler loginCommandHandler() {
        return new LoginCommandHandler(kernelConfiguration.commandBus(), kernelConfiguration.queryBus());
    }

    @Bean
    public CreateTokenCommandHandler createTokenCommandHandler() {
        return new CreateTokenCommandHandler(tokenService());
    }

    @Bean
    public DecodeTokenCommandHandler decodeTokenCommandHandler() {
        return new DecodeTokenCommandHandler(tokenService());
    }

    @Bean
    public CommandBus authenticationCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(LoginCommand.class, loginCommandHandler());
        commandBus.addHandler(CreateTokenCommand.class, createTokenCommandHandler());
        commandBus.addHandler(DecodeTokenCommand.class, decodeTokenCommandHandler());
        return commandBus;
    }
}
