package com.esgi.modules.comment.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.CommentRepository;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;

import java.time.LocalDateTime;

public final class CreateCommentCommandHandler implements CommandHandler<CreateComment, CommentId> {
    private final CommentRepository commentRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreateCommentCommandHandler(CommentRepository commentRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.commentRepository = commentRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public CommentId handle(CreateComment createComment) {
        final CommentId commentId = commentRepository.nextIdentity();
        final PostId postId = new PostId(createComment.postId);
        final UserId creatorId = new UserId(createComment.creatorId);
        Comment comment = new Comment(commentId, postId, createComment.content, creatorId, LocalDateTime.now());
        commentRepository.add(comment);
        eventEventDispatcher.dispatch(new CreateCommentEvent(commentId));
        return commentId;
    }
}
