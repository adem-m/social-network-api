package com.esgi.follow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.esgi.modules.follow.application.RetrieveFollowers;
import com.esgi.modules.follow.application.RetrieveFollowersHandler;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.follow.domain.FollowId;
import com.esgi.modules.follow.infrastructure.InMemoryFollowRepository;
import com.esgi.modules.user.domain.UserId;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveFollowersHandlerTest {
    RetrieveFollowersHandler handler;
    InMemoryFollowRepository repository;

    final RetrieveFollowers request = new RetrieveFollowers("followedId");

    @BeforeEach
    void setup() {
        repository = new InMemoryFollowRepository();
        handler = new RetrieveFollowersHandler(repository);
    }

    @Test
    void should_return_followers() {
        repository.add(
            new Follow(
                new FollowId("id1"), 
                new UserId("followerId1"), 
                new UserId("followedId")
        ));
        repository.add(
            new Follow(
                new FollowId("id2"), 
                new UserId("followerId2"), 
                new UserId("followedId")
        ));

        var followers = handler.handle(request);
        assertThat(followers).hasSize(2);
    }

    @Test
    void should_return_no_followers_when_user_is_not_followed() {
        var followers = handler.handle(request);
        assertThat(followers).hasSize(0);
    }
}
