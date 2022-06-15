package com.esgi.modules.post.application;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.code.application.CreateCode;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostRepository;
import com.esgi.modules.user.domain.UserId;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class CreatePostCommandHandler implements CommandHandler<CreatePost, PostId> {
    private final PostRepository postRepository;
    private final EventDispatcher<Event> eventEventDispatcher;
    private final CommandBus commandBus;

    public CreatePostCommandHandler(PostRepository postRepository, EventDispatcher<Event> eventEventDispatcher, CommandBus commandBus) {
        this.postRepository = postRepository;
        this.eventEventDispatcher = eventEventDispatcher;
        this.commandBus = commandBus;
    }

    @Transactional
    public PostId handle(CreatePost createPost) {
        final PostId postId = postRepository.nextIdentity();
        final UserId creatorId = new UserId(createPost.creatorId);
        Post post = new Post(postId, createPost.content, creatorId, LocalDateTime.now(ZoneId.of("Europe/Paris")));
        postRepository.add(post);
        if (createPost.code != null) {
            CreateCode createCode = new CreateCode(postId, createPost.code.source, createPost.code.language);
            commandBus.send(createCode);
        }
        eventEventDispatcher.dispatch(new CreatePostEvent(postId));
        return postId;
    }
}
