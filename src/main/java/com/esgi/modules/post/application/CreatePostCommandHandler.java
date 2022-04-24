package com.esgi.modules.post.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.code.application.CreateCode;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostRepository;
import com.esgi.modules.user.domain.UserId;

import java.util.Date;
import java.util.Objects;

public final class CreatePostCommandHandler implements CommandHandler<CreatePost, PostId> {
    private final PostRepository postRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreatePostCommandHandler(PostRepository postRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.postRepository = postRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public PostId handle(CreatePost createPost) {
        final PostId postId = postRepository.nextIdentity();
        final UserId creatorId = new UserId(createPost.creatorId);
        Post post = new Post(postId, createPost.content, creatorId, new Date());
        if(!Objects.equals(createPost.code.source, "")) {
            CreateCode createCode = new CreateCode(postId, createPost.code.source, createPost.code.language);
            createPost.commandBus.send(createCode);
        }
        postRepository.add(post);
        eventEventDispatcher.dispatch(new CreatePostEvent(postId));
        return postId;
    }
}
