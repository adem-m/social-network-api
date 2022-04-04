package com.esgi;

import com.esgi.kernel.*;
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
    public CommandBus createUserCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(CreateUser.class, createUserCommandHandler());
        return commandBus;
    }

    @Bean
    public UpdateUserCommandHandler editUserCommandHandler() {
        return new UpdateUserCommandHandler(userRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus editUserCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(UpdateUser.class, editUserCommandHandler());
        return commandBus;
    }

    @Bean
    public DeleteUserCommandHandler deleteUserCommandHandler() {
        return new DeleteUserCommandHandler(userRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus deleteUserCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(DeleteUser.class, deleteUserCommandHandler());
        return commandBus;
    }

    @Bean
    public QueryBus usersQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveUsers.class, new RetrieveUsersHandler(userRepository()));
        return queryBus;
    }

    @Bean
    public RetrieveUsersHandler retrieveUsersHandler() {
        return new RetrieveUsersHandler(userRepository());
    }

    @Bean
    public QueryBus userQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveUserById.class, new RetrieveUserByIdHandler(userRepository()));
        return queryBus;
    }

    @Bean
    public RetrieveUserByIdHandler retrieveUserByIdHandler() {
        return new RetrieveUserByIdHandler(userRepository());
    }

    @Bean
    public QueryBus usersByNameQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveUsersByName.class, new RetrieveUsersByNameHandler(userRepository()));
        return queryBus;
    }

    @Bean
    public RetrieveUsersByNameHandler retrieveUsersByNameHandler() {
        return new RetrieveUsersByNameHandler(userRepository());
    }
}
