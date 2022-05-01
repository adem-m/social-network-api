package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.codeCompiler.application.RunCode;
import com.esgi.modules.codeCompiler.application.RunCodeCommandHandler;
import com.esgi.modules.codeCompiler.application.RunCodeEvent;
import com.esgi.modules.codeCompiler.application.RunCodeEventListener;
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
    public CommandBus codeCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(RunCode.class, runCodeCommandHandler());
        return commandBus;
    }
}
