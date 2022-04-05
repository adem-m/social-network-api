package com.esgi.modules.comment.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.comment.domain.CommentLikeId;
import com.esgi.modules.comment.domain.CommentLikeRepository;

public final class UnlikeCommentCommandHandler implements CommandHandler<UnlikeComment, CommentLikeId> {
    private final CommentLikeRepository commentLikeRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public UnlikeCommentCommandHandler(CommentLikeRepository commentLikeRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.commentLikeRepository = commentLikeRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public CommentLikeId handle(UnlikeComment UnlikeComment) {
        final CommentLikeId commentLikeId = new CommentLikeId(UnlikeComment.commentId);
        commentLikeRepository.delete(commentLikeId);
        eventEventDispatcher.dispatch(new UnlikeCommentEvent(commentLikeId));
        return commentLikeId;
    }
}
