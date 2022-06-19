package com.esgi.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.esgi.modules.user.application.RetrieveUsersByName;
import com.esgi.modules.user.application.RetrieveUsersByNameHandler;
import com.esgi.modules.user.domain.Email;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.infrastructure.InMemoryUserRepository;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveUsersByNameHandlerTest {
    RetrieveUsersByNameHandler handler;
    InMemoryUserRepository repository;

    final RetrieveUsersByName request = new RetrieveUsersByName("firstname lastname");

    @BeforeEach
    void setup() {
        repository = new InMemoryUserRepository();
        handler = new RetrieveUsersByNameHandler(repository);
    }

    @Test
    void should_return_all_matching_users() {
        repository.add(new User(new UserId("id"), "lastname", "firstname", new Email("username@example.com"), "password"));
        repository.add(new User(new UserId("id2"), "lastname", "firstname", new Email("username2@example.com"), "password"));
        repository.add(new User(new UserId("id3"), "lastname2", "firstname", new Email("username3@example.com"), "password"));

        var users = handler.handle(request);
        assertThat(users).hasSize(2);
    }

    @Test
    void should_return_no_users_when_none_are_matching() {
        repository.add(new User(new UserId("id"), "lastname1", "firstname", new Email("username@example.com"), "password"));
        repository.add(new User(new UserId("id2"), "lastname3", "firstname", new Email("username2@example.com"), "password"));
        repository.add(new User(new UserId("id3"), "lastname2", "firstname", new Email("username3@example.com"), "password"));

        var users = handler.handle(request);
        assertThat(users).hasSize(0);
    }

    @Test
    void should_return_no_users_when_db_is_empty() {
        var users = handler.handle(request);
        assertThat(users).hasSize(0);
    }
}
