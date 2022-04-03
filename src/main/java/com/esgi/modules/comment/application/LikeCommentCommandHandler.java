package com.esgi.modules.comment.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.comment.domain.CommentLike;
import com.esgi.modules.comment.domain.CommentLikeId;
import com.esgi.modules.comment.domain.CommentLikeRepository;

public final class LikeCommentCommandHandler implements CommandHandler<LikeComment, CommentLikeId> {
    private final CommentLikeRepository commentLikeRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public LikeCommentCommandHandler(CommentLikeRepository commentLikeRepository, EventDispatcher<Event> eventEventDispatcher) {
            this.commentLikeRepository = commentLikeRepository;
            this.eventEventDispatcher = eventEventDispatcher;
            }

    public CommentLikeId handle(LikeComment likeComment) {
        final CommentLikeId commentLikeId = commentLikeRepository.nextIdentity();
        CommentLike commentLike;
        commentLike = new CommentLike(commentLikeId ,likeComment.userId, likeComment.commentId);
        commentLikeRepository.add(commentLike);
        eventEventDispatcher.dispatch(new LikeCommentEvent(commentLikeId));
        return commentLikeId;
    }
}
