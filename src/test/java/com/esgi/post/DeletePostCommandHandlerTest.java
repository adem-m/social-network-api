package com.esgi.post;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.esgi.kernel.DefaultEventDispatcher;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.ForbiddenOperationException;
import com.esgi.modules.post.application.DeletePost;
import com.esgi.modules.post.application.DeletePostCommandHandler;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.infrastructure.InMemoryPostRepository;
import com.esgi.modules.user.domain.UserId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeletePostCommandHandlerTest {
  DeletePostCommandHandler handler;
  InMemoryPostRepository repository;
  EventDispatcher<Event> dispatcher = new DefaultEventDispatcher<>(new HashMap<>());

  final Post post = new Post(
    new PostId("postId"),
    null, 
    new UserId("userId"), 
    LocalDateTime.now());
  final DeletePost request = new DeletePost("postId", "userId");

  @BeforeEach
  void setup() {
    repository = new InMemoryPostRepository();
    handler = new DeletePostCommandHandler(repository, dispatcher);
  }

  @Test
  public void should_remove_post() {
    repository.add(post);
    handler.handle(request);
    assertThat(repository.findAll()).isEmpty();
  }

  @Test
  public void should_fail_when_deleting_a_post_not_owned() {
    repository.add(post);
    var request = new DeletePost("postId", "userId2");

    assertThatThrownBy(() -> handler.handle(request))
      .isInstanceOf(ForbiddenOperationException.class);
  }
}
