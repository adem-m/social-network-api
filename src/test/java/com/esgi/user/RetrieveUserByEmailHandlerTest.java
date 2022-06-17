package com.esgi.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.user.application.RetrieveUserByEmail;
import com.esgi.modules.user.application.RetrieveUserByEmailHandler;
import com.esgi.modules.user.domain.Email;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.infrastructure.InMemoryUserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RetrieveUserByEmailHandlerTest {
    RetrieveUserByEmailHandler handler;
    InMemoryUserRepository repository;

    final RetrieveUserByEmail request = new RetrieveUserByEmail("username@example.com");

    @BeforeEach
    void setup() {
        repository = new InMemoryUserRepository();
        handler = new RetrieveUserByEmailHandler(repository);
    }

    @Test
    void should_retrieve_user() {
        repository.add(new User(new UserId("id"), "lastname", "firstname", new Email("username@example.com"), "password"));
        var user = handler.handle(request);
        assertThat(user).isInstanceOf(User.class);
    }

    @Test
    void should_throw_exception_when_user_does_not_exist() {
        assertThatThrownBy(() -> handler.handle(request))
            .isInstanceOf(NoSuchEntityException.class);
    }
}
