package com.esgi.modules.comment.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentRepository;

public final class EditCommentCommandHandler implements CommandHandler<EditComment, CommentId> {
    private final CommentRepository commentRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public EditCommentCommandHandler(CommentRepository commentRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.commentRepository = commentRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public CommentId handle(EditComment editComment) {
        var commentId = new CommentId(editComment.commentId);
        Comment comment = commentRepository.findById(commentId);
        comment.changeContent(editComment.content);
        commentRepository.add(comment);
        eventEventDispatcher.dispatch(new EditCommentEvent(commentId));
        return commentId;
    }
}
