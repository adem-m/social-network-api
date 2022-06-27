package com.esgi.user;

import java.util.HashMap;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.DefaultEventDispatcher;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.user.application.DeleteUser;
import com.esgi.modules.user.application.DeleteUserCommandHandler;
import com.esgi.modules.user.domain.Email;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.infrastructure.InMemoryUserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteUserHandlerTest {
    DeleteUserCommandHandler handler;
    InMemoryUserRepository repository;
    EventDispatcher<Event> dispatcher;
    CommandBus commandBus;
    User user;

    @BeforeEach
    void setup() {
        repository = new InMemoryUserRepository();
        dispatcher = new DefaultEventDispatcher<Event>(new HashMap());
        handler = new DeleteUserCommandHandler(repository, dispatcher, commandBus);
        user = new User(
            new UserId(repository.nextIdentity().toString()),
            "lastname", 
            "firstname", 
            new Email("test@example.com"), 
            "azertyUIOP123$"
        );
    }

    @Test
    void should_delete_existing_user() {
        repository.add(user);
        var request = new DeleteUser(user.getId().getValue());
        
        assertThat(handler.handle(request)).isInstanceOf(UserId.class);
        assertThat(repository.findAll()).hasSize(0);
    }
}
