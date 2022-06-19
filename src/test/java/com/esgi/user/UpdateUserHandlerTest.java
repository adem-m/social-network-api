package com.esgi.user;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.NoSuchEntityException;
import com.esgi.kernel.DefaultEventDispatcher;
import com.esgi.modules.user.application.UpdateUser;
import com.esgi.modules.user.application.UpdateUserCommandHandler;
import com.esgi.modules.user.domain.Email;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.infrastructure.InMemoryUserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UpdateUserHandlerTest {
    UpdateUserCommandHandler handler;
    InMemoryUserRepository repository;
    EventDispatcher<Event> dispatcher;

    final UpdateUser request = new UpdateUser("id1", new Email("username4@example.com"), "password");

    @BeforeEach
    void setup() {
        dispatcher = new DefaultEventDispatcher<Event>(new HashMap<>());
        repository = new InMemoryUserRepository();
        handler = new UpdateUserCommandHandler(repository, dispatcher);
    }

    @Test
    void should_update_user() {
        repository.add(new User(new UserId("id1"), "lastname", "firstname", new Email("username@example.com"), "password"));

        handler.handle(request);
        var user = repository.findById(new UserId("id1"));
        assertThat(user.getEmail().getEmail()).isEqualTo(request.email.getEmail());
    }

    @Test
    void should_throw_an_exception_when_user_is_not_found() {
        assertThatThrownBy(() -> handler.handle(request))
            .isInstanceOf(NoSuchEntityException.class);
    }
}
