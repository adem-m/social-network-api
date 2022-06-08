package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.user.application.*;
import com.esgi.modules.user.domain.UserRepository;
import com.esgi.modules.user.infrastructure.SpringDataUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class UserConfiguration {
    private final KernelConfiguration kernelConfiguration;

    public UserConfiguration(KernelConfiguration kernelConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
    }

    @Bean
    @Primary
    public UserRepository userRepository() {
        return new SpringDataUserRepository();
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
        return new CreateUserCommandHandler(userRepository(), kernelConfiguration.eventDispatcher(), kernelConfiguration.commandBus());
    }

    @Bean
    public CommandBus createUserCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(CreateUser.class, createUserCommandHandler());
        return commandBus;
    }

    @Bean
    public UpdateUserEventListener updateUserEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        UpdateUserEventListener listener = new UpdateUserEventListener();
        dispatcher.addListener(UpdateUserEvent.class, listener);
        return listener;
    }

    @Bean
    public UpdateUserCommandHandler updateUserCommandHandler() {
        return new UpdateUserCommandHandler(userRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus updateUserCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(UpdateUser.class, updateUserCommandHandler());
        return commandBus;
    }

    @Bean
    public DeleteUserEventListener deleteUserEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        DeleteUserEventListener listener = new DeleteUserEventListener();
        dispatcher.addListener(DeleteUserEvent.class, listener);
        return listener;
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
    public QueryBus userIdQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveUserById.class, new RetrieveUserByIdHandler(userRepository()));
        return queryBus;
    }

    @Bean
    public RetrieveUserByIdHandler retrieveUserByIdHandler() {
        return new RetrieveUserByIdHandler(userRepository());
    }

    @Bean
    public QueryBus userEmailQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveUserByEmail.class, new RetrieveUserByEmailHandler(userRepository()));
        return queryBus;
    }

    @Bean
    public RetrieveUserByEmailHandler retrieveUserByEmailHandler() {
        return new RetrieveUserByEmailHandler(userRepository());
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
