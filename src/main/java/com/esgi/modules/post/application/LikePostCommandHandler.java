package com.esgi.modules.post.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.post.domain.*;

import javax.persistence.EntityExistsException;

public final class LikePostCommandHandler implements CommandHandler<LikePost, PostLikeId> {
    private final PostLikeRepository postLikeRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public LikePostCommandHandler(PostLikeRepository postLikeRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.postLikeRepository = postLikeRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public PostLikeId handle(LikePost likePost) {
        final PostLikeId postLikeId = postLikeRepository.nextIdentity();
        PostLike postLike;
        postLike = new PostLike(postLikeId, likePost.userId, likePost.postId);
        postLikeRepository.add(postLike);
        eventEventDispatcher.dispatch(new LikePostEvent(postLikeId));
        return postLikeId;
    }
}
