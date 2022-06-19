package com.esgi.follow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.esgi.modules.follow.application.RetrieveFollowing;
import com.esgi.modules.follow.application.RetrieveFollowingHandler;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.follow.domain.FollowId;
import com.esgi.modules.follow.infrastructure.InMemoryFollowRepository;
import com.esgi.modules.user.domain.UserId;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveFollowingHandlerTest {
    RetrieveFollowingHandler handler;
    InMemoryFollowRepository repository;

    final RetrieveFollowing request = new RetrieveFollowing("followerId");

    @BeforeEach
    void setup() {
        repository = new InMemoryFollowRepository();
        handler = new RetrieveFollowingHandler(repository);
    }

    @Test
    void should_return_following() {
        repository.add(
            new Follow(
                new FollowId("id1"), 
                new UserId("followerId"), 
                new UserId("followedId1")
        ));
        repository.add(
            new Follow(
                new FollowId("id2"), 
                new UserId("followerId"), 
                new UserId("followedId2")
        ));

        var followees = handler.handle(request);
        assertThat(followees).hasSize(2);
    }

    @Test
    void should_return_no_followees_when_user_follows_no_one() {
        var followers = handler.handle(request);
        assertThat(followers).hasSize(0);
    }
}
