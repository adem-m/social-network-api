package com.esgi.modules.comment.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.ForbiddenOperationException;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentRepository;

public record DeleteCommentCommandHandler(CommentRepository commentRepository,
                                          EventDispatcher<Event> eventEventDispatcher)
        implements CommandHandler<DeleteComment, CommentId> {

    public CommentId handle(DeleteComment deleteComment) {
        final CommentId commentId = new CommentId(deleteComment.commentId());
        Comment comment = commentRepository.findById(commentId);
        if (!comment.getUserId().equals(deleteComment.userId())) {
            throw new ForbiddenOperationException("User is not the owner of the comment");
        }
        commentRepository.delete(commentId);
        eventEventDispatcher.dispatch(new DeleteCommentEvent(commentId));
        return commentId;
    }
}
