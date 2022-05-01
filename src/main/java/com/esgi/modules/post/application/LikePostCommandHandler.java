package com.esgi.modules.post.application;

import com.esgi.kernel.*;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostLike;
import com.esgi.modules.post.domain.PostLikeId;
import com.esgi.modules.post.domain.PostLikeRepository;
import com.esgi.modules.user.domain.UserId;

public final class LikePostCommandHandler implements CommandHandler<LikePost, PostLikeId> {
    private final PostLikeRepository postLikeRepository;
    private final EventDispatcher<Event> eventEventDispatcher;
    private final CommandBus commandBus;

    public LikePostCommandHandler(PostLikeRepository postLikeRepository, EventDispatcher<Event> eventEventDispatcher, CommandBus commandBus) {
        this.postLikeRepository = postLikeRepository;
        this.eventEventDispatcher = eventEventDispatcher;
        this.commandBus = commandBus;
    }

    public PostLikeId handle(LikePost likePost) {
        final UserId userId = new UserId(likePost.userId);
        final PostId postId = new PostId(likePost.postId);
        if(postLikeRepository.findLikeByUserIdAndPostId(userId, postId) == null) {
            final PostLikeId postLikeId = postLikeRepository.nextIdentity();
            PostLike postLike = new PostLike(postLikeId, userId, postId);
            postLikeRepository.add(postLike);
            eventEventDispatcher.dispatch(new LikePostEvent(postLikeId));
            return postLikeId;
        }
        UnlikePost unlikePost = new UnlikePost(userId.getValue(), postId.getValue());
        commandBus.send(unlikePost);
        return null;
    }
}
