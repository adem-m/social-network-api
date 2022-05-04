package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.codeCompiler.application.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CodeCompilerConfiguration {
    private final KernelConfiguration kernelConfiguration;

    public CodeCompilerConfiguration(KernelConfiguration kernelConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
    }

    @Bean
    public RunCodeEventListener runCodeEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        RunCodeEventListener listener = new RunCodeEventListener();
        dispatcher.addListener(RunCodeEvent.class, listener);
        return listener;
    }

    @Bean
    public RunCodeCommandHandler runCodeCommandHandler() {
        return new RunCodeCommandHandler(kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus codeCompilerCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(RunCode.class, runCodeCommandHandler());
        commandBus.addHandler(RunChallenge.class,
                new RunChallengeCommandHandler(kernelConfiguration.queryBus(), kernelConfiguration.commandBus()));
        return commandBus;
    }
}
