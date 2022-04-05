package com.esgi.modules.post.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostRepository;

public final class EditPostCommandHandler implements CommandHandler<EditPost, PostId> {
    private final PostRepository postRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public EditPostCommandHandler(PostRepository postRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.postRepository = postRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public PostId handle(EditPost editPost) {
        var postId = new PostId(editPost.postId);
        Post post = postRepository.findById(postId);
        post.changeContent(editPost.content);
        postRepository.add(post);
        eventEventDispatcher.dispatch(new EditPostEvent(postId));
        return postId;
    }
}
