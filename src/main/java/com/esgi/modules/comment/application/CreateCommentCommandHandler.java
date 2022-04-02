package com.esgi.modules.comment.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentRepository;

public class CreateCommentCommandHandler implements CommandHandler<CreateComment, CommentId> {
    private final CommentRepository commentRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreateCommentCommandHandler(CommentRepository commentRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.commentRepository = commentRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public CommentId handle(CreateComment createComment) {
        final CommentId commentId = commentRepository.nextIdentity();
        Comment comment;
        comment = new Comment(commentId, createComment.postId, createComment.content, createComment.creatorId);
        commentRepository.add(comment);
        eventEventDispatcher.dispatch(new CreateCommentEvent(commentId));
        return commentId;
    }
}
