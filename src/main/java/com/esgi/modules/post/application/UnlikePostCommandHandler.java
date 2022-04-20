package com.esgi.modules.post.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostLikeId;
import com.esgi.modules.post.domain.PostLikeRepository;
import com.esgi.modules.user.domain.UserId;

public final class UnlikePostCommandHandler implements CommandHandler<UnlikePost, PostLikeId> {
    private final PostLikeRepository postLikeRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public UnlikePostCommandHandler(PostLikeRepository postLikeRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.postLikeRepository = postLikeRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public PostLikeId handle(UnlikePost unlikePost) {
        final UserId userId = new UserId(unlikePost.userId);
        final PostId postId = new PostId(unlikePost.postId);
        final PostLikeId postLikeId = postLikeRepository.findLikeByUserIdAndPostId(userId, postId).getPostLikeId();
        postLikeRepository.delete(postLikeId);
        eventEventDispatcher.dispatch(new UnlikePostEvent(postLikeId));
        return postLikeId;
    }
}
