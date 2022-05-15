package com.esgi.modules.post.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.ForbiddenOperationException;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostRepository;

public class DeletePostCommandHandler implements CommandHandler<DeletePost, PostId> {
    private final PostRepository postRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public DeletePostCommandHandler(PostRepository postRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.postRepository = postRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public PostId handle(DeletePost deletePost) {
        final PostId postId = new PostId(deletePost.postId);
        Post post = postRepository.findById(postId);
        if (!post.getUserId().getValue().equals(deletePost.userId)) {
            throw new ForbiddenOperationException("You can't delete this post");
        }
        postRepository.delete(postId);
        eventEventDispatcher.dispatch(new DeletePostEvent(postId));
        return postId;
    }
}
