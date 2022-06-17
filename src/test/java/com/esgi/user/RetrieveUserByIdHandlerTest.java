package com.esgi.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.user.application.RetrieveUserById;
import com.esgi.modules.user.application.RetrieveUserByIdHandler;
import com.esgi.modules.user.domain.Email;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.infrastructure.InMemoryUserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RetrieveUserByIdHandlerTest {
    RetrieveUserByIdHandler handler;
    InMemoryUserRepository repository;

    final RetrieveUserById request = new RetrieveUserById("some id");

    @BeforeEach
    void setup() {
        repository = new InMemoryUserRepository();
        handler = new RetrieveUserByIdHandler(repository);
    }

    @Test
    void should_retrieve_user() {
        repository.add(new User(new UserId("some id"), "lastname", "firstname", new Email("username@example.com"), "password"));
        var user = handler.handle(request);
        assertThat(user).isInstanceOf(User.class);
    }

    @Test
    void should_throw_exception_when_user_does_not_exist() {
        assertThatThrownBy(() -> handler.handle(request))
            .isInstanceOf(NoSuchEntityException.class);
    }
}
