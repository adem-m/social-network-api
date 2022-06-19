package com.esgi.follow;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.esgi.kernel.DefaultEventDispatcher;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.follow.application.Unfollow;
import com.esgi.modules.follow.application.UnfollowCommandHandler;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.follow.domain.FollowId;
import com.esgi.modules.follow.infrastructure.InMemoryFollowRepository;
import com.esgi.modules.user.domain.UserId;

import static org.assertj.core.api.Assertions.assertThat;

public class UnfollowHandlerTest {
    UnfollowCommandHandler handler;
    InMemoryFollowRepository repository;
    EventDispatcher<Event> dispatcher;
    
    final Unfollow request = new Unfollow("unfollowerId", "unfollowedId");

    @BeforeEach
    void setup() {
        repository = new InMemoryFollowRepository();
        dispatcher = new DefaultEventDispatcher<>(new HashMap<>());
        handler = new UnfollowCommandHandler(repository, dispatcher);
    }

    @Test
    void should_unfollow() {
        repository.add(
            new Follow(
                new FollowId("id1"), 
                new UserId("unfollowerId"), 
                new UserId("unfollowedId")
        ));
        handler.handle(request);

        var follows = repository.findAll();
        assertThat(follows).hasSize(0);
    }
}
