package com.esgi.modules.post.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostLike;
import com.esgi.modules.post.domain.PostLikeId;
import com.esgi.modules.post.domain.PostLikeRepository;
import com.esgi.modules.user.domain.UserId;

public final class LikePostCommandHandler implements CommandHandler<LikePost, PostLikeId> {
    private final PostLikeRepository postLikeRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public LikePostCommandHandler(PostLikeRepository postLikeRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.postLikeRepository = postLikeRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public PostLikeId handle(LikePost likePost) {
        final PostLikeId postLikeId = postLikeRepository.nextIdentity();
        final UserId userId = new UserId(likePost.userId);
        final PostId postId = new PostId(likePost.postId);
        PostLike postLike = new PostLike(postLikeId, userId, postId);
        postLikeRepository.add(postLike);
        eventEventDispatcher.dispatch(new LikePostEvent(postLikeId));
        return postLikeId;
    }
}
