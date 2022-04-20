package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.code.application.*;
import com.esgi.modules.code.domain.CodeRepository;
import com.esgi.modules.code.infrastructure.InMemoryCodeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CodeConfiguration {
    private final KernelConfiguration kernelConfiguration;

    public CodeConfiguration(KernelConfiguration kernelConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
    }

    @Bean
    public CodeRepository codeRepository() {
        return new InMemoryCodeRepository();
    }

    @Bean
    public CreateCodeEventListener createCodeEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        CreateCodeEventListener listener = new CreateCodeEventListener();
        dispatcher.addListener(CreateCodeEvent.class, listener);
        return listener;
    }

    @Bean
    public CreateCodeCommandHandler createCodeCommandHandler() {
        return new CreateCodeCommandHandler(codeRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus createCodeCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(CreateCode.class, createCodeCommandHandler());
        return commandBus;
    }

    @Bean
    public QueryBus codePostIdQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveCodeByPostId.class, new RetrieveCodeByPostIdHandler(codeRepository()));
        return queryBus;
    }

    @Bean
    public RetrieveCodeByPostIdHandler retrieveCodeByPostIdHandler() {
        return new RetrieveCodeByPostIdHandler(codeRepository());
    }
}
