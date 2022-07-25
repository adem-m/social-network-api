package com.esgi.post;

import java.util.HashMap;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.DefaultCommandBus;
import com.esgi.kernel.DefaultEventDispatcher;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.code.exposition.CodeRequest;
import com.esgi.modules.post.application.CreatePost;
import com.esgi.modules.post.application.CreatePostCommandHandler;
import com.esgi.modules.post.infrastructure.InMemoryPostRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreatePostCommandHandlerTest {
  private InMemoryPostRepository repository;
  private EventDispatcher<Event> dispatcher = new DefaultEventDispatcher<Event>(new HashMap<>());
  private CommandBus commandBus = new DefaultCommandBus<>(new HashMap<>());
  private CreatePostCommandHandler handler = new CreatePostCommandHandler(
    repository, 
    dispatcher, 
    commandBus);

  @BeforeEach
  void setup() {
    repository = new InMemoryPostRepository();
    handler = new CreatePostCommandHandler(
      repository, 
      dispatcher, 
      commandBus);
  }

  @Test
  public void should_add_post() {
    var request = new CreatePost(
      "Some content", 
      null, 
      "creatorId");

    handler.handle(request);

    var posts = repository.findAll();
    assertThat(posts).hasSize(1);
  }
}
