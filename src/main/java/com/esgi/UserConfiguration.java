package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.infrastructure.InMemoryUserRepository;
import com.esgi.modules.user.application.*;
import com.esgi.modules.user.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    private final KernelConfiguration kernelConfiguration;

    public UserConfiguration(KernelConfiguration kernelConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
    }

    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public CreateUserEventListener createUserEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        CreateUserEventListener listener = new CreateUserEventListener();
        dispatcher.addListener(CreateUserEvent.class, listener);
        return listener;
    }

    @Bean
    public CreateUserCommandHandler createUserCommandHandler() {
        return new CreateUserCommandHandler(userRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus userCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(CreateUser.class, createUserCommandHandler());
        return commandBus;
    }

    @Bean
    public QueryBus userQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveUsers.class, new RetrieveUsersHandler(userRepository()));
        return queryBus;
    }

    @Bean
    public RetrieveUsersHandler retrieveUsersHandler() {
        return new RetrieveUsersHandler(userRepository());
    }
}
