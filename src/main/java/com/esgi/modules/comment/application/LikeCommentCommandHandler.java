package com.esgi.modules.comment.application;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentLike;
import com.esgi.modules.comment.domain.CommentLikeId;
import com.esgi.modules.comment.domain.CommentLikeRepository;
import com.esgi.modules.user.domain.UserId;

public final class LikeCommentCommandHandler implements CommandHandler<LikeComment, CommentLikeId> {
    private final CommentLikeRepository commentLikeRepository;
    private final EventDispatcher<Event> eventEventDispatcher;
    private final CommandBus commandBus;

    public LikeCommentCommandHandler(CommentLikeRepository commentLikeRepository,
                                     EventDispatcher<Event> eventEventDispatcher,
                                     CommandBus commandBus) {
            this.commentLikeRepository = commentLikeRepository;
            this.eventEventDispatcher = eventEventDispatcher;
            this.commandBus = commandBus;
            }

    public CommentLikeId handle(LikeComment likeComment) {
        final UserId userId = new UserId(likeComment.userId);
        final CommentId commentId = new CommentId(likeComment.commentId);
        if(commentLikeRepository.findLikeByUserIdAndCommentId(userId, commentId) == null) {
            final CommentLikeId commentLikeId = commentLikeRepository.nextIdentity();
            CommentLike commentLike = new CommentLike(commentLikeId, userId, commentId);
            commentLikeRepository.add(commentLike);
            eventEventDispatcher.dispatch(new LikeCommentEvent(commentLikeId));
            return commentLikeId;
        }
        UnlikeComment unlikeComment = new UnlikeComment(userId.getValue(), commentId.getValue());
        commandBus.send(unlikeComment);
        return null;
    }
}
