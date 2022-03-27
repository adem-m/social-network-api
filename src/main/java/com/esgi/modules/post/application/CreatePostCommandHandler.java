package com.esgi.modules.post.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostRepository;

public class CreatePostCommandHandler  implements CommandHandler<CreatePost, PostId> {
    private final PostRepository postRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreatePostCommandHandler(PostRepository postRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.postRepository = postRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public PostId handle(CreatePost createPost) {
        final PostId postId = postRepository.nextIdentity();
        Post post;
        post = new Post(postId, createPost.content, createPost.creatorId);
        postRepository.add(post);
        eventEventDispatcher.dispatch(new CreatePostEvent(postId));
        return postId;
    }
}
