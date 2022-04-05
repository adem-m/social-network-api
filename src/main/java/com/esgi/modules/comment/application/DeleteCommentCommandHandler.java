package com.esgi.modules.comment.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentRepository;

public final class DeleteCommentCommandHandler implements CommandHandler<DeleteComment, CommentId> {
    private final CommentRepository commentRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public DeleteCommentCommandHandler(CommentRepository commentRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.commentRepository = commentRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public CommentId handle(DeleteComment deleteComment) {
        final CommentId commentId = new CommentId(deleteComment.commentId);
        commentRepository.delete(commentId);
        eventEventDispatcher.dispatch(new DeleteCommentEvent(commentId));
        return commentId;
    }
}
