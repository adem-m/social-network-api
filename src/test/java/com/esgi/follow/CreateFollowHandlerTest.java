package com.esgi.follow;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.esgi.kernel.DefaultEventDispatcher;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.follow.application.CreateFollow;
import com.esgi.modules.follow.application.CreateFollowCommandHandler;
import com.esgi.modules.follow.infrastructure.InMemoryFollowRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CreateFollowHandlerTest {
    CreateFollowCommandHandler handler;
    EventDispatcher<Event> dispatcher;
    InMemoryFollowRepository repository;
    
    final CreateFollow request = new CreateFollow("followerId", "followedId");

    @BeforeEach
    void setup() {
        repository = new InMemoryFollowRepository();
        dispatcher = new DefaultEventDispatcher<Event>(new HashMap<>());
        handler = new CreateFollowCommandHandler(repository, dispatcher);
    }

    @Test
    void should_add_follow() {
        handler.handle(request);

        var follows = repository.findAll(); 
        assertThat(follows).hasSize(1);
    }

    @Test
    void should_throw_exception_when_follow_already_exists() {
        handler.handle(request);
        assertThatThrownBy(() -> handler.handle(request))
            .isInstanceOf(IllegalStateException.class);
    }
}
