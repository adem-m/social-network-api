package com.esgi.user;

import java.util.HashMap;

import com.esgi.kernel.AlreadyExistsException;
import com.esgi.kernel.DefaultEventDispatcher;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.user.application.CreateUser;
import com.esgi.modules.user.application.CreateUserCommandHandler;
import com.esgi.modules.user.domain.Email;
import com.esgi.modules.user.infrastructure.InMemoryUserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CreateUserHandlerTest {
    CreateUserCommandHandler handler;
    InMemoryUserRepository repository;
    EventDispatcher<Event> dispatcher;

    final CreateUser request = new CreateUser("lastname", "firstname", new Email("test@example.com"), "azertyUIOP123$");
    
    @BeforeEach
    void setup() {
        repository = new InMemoryUserRepository();
        dispatcher = new DefaultEventDispatcher<Event>(new HashMap<>());
        handler = new CreateUserCommandHandler(repository, dispatcher);
    }

    @Test
    void should_add_user() {
        handler.handle(request);

        var users = repository.findAll();
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getFirstname()).isEqualTo(request.firstname);
        assertThat(users.get(0).getLastname()).isEqualTo(request.lastname);
        assertThat(users.get(0).getEmail().getEmail()).isEqualTo(request.email.getEmail());
    }

    @Test
    void should_throw_exception_when_inserting_duplicate_email() {
        handler.handle(request);

        assertThatThrownBy(() -> handler.handle(request))
            .isInstanceOf(AlreadyExistsException.class);
    }
}
