package com.esgi.modules.comment.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.authentication.application.DecodeTokenCommand;
import com.esgi.modules.authentication.domain.Token;
import com.esgi.modules.comment.application.*;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.user.domain.UserId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public CommentController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestHeader("authorization") String token,
                                       @RequestBody @Valid CommentRequest request) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        CreateComment createComment = new CreateComment(request.postId, request.content, userId.getValue());
        CommentId commentId = (CommentId) commandBus.send(createComment);
        return ResponseEntity.created(URI.create("/comments/id=" + commentId.getValue())).build();
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable String id) {
        final Comment comment = (Comment) queryBus.send(new RetrieveCommentById(id));
        CommentResponse commentResponseResult = new CommentResponse(
                String.valueOf(comment.getCommentId().getValue()),
                String.valueOf(comment.getPostId().getValue()),
                comment.getContent(),
                comment.getUserId().getValue(),
                comment.getDate());
        return ResponseEntity.ok(commentResponseResult);
    }

    @GetMapping(path = "/user={id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentsResponse> getCommentsByUserId(@PathVariable String id) {
        final List<Comment> comments = (List<Comment>) queryBus.send(new RetrieveComments(id));
        CommentsResponse commentsResponseResult = new CommentsResponse(
                comments.stream().map(comment -> new CommentResponse(
                        String.valueOf(comment.getCommentId().getValue()),
                        String.valueOf(comment.getUserId().getValue()),
                        comment.getContent(),
                        comment.getUserId().getValue(),
                        comment.getDate())).collect(Collectors.toList()));
        return ResponseEntity.ok(commentsResponseResult);
    }

    @GetMapping(path = "/post={id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentsResponse> getCommentsByPostId(@PathVariable String id) {
        final List<Comment> comments = (List<Comment>) queryBus.send(new RetrieveCommentsByPostId(id));
        CommentsResponse commentsResponseResult = new CommentsResponse(
                comments.stream().map(comment -> new CommentResponse(
                        String.valueOf(comment.getCommentId().getValue()),
                        String.valueOf(comment.getUserId().getValue()),
                        comment.getContent(),
                        comment.getUserId().getValue(),
                        comment.getDate())).collect(Collectors.toList()));
        return ResponseEntity.ok(commentsResponseResult);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentResponse> edit(@PathVariable String id, @RequestBody @Valid EditCommentRequest request) {
        EditComment editComment = new EditComment(id, request.content);
        commandBus.send(editComment);
        final Comment comment = (Comment) queryBus.send(new RetrieveCommentById(editComment.commentId));
        CommentResponse commentResponseResult = new CommentResponse(
                String.valueOf(comment.getCommentId().getValue()),
                String.valueOf(comment.getPostId().getValue()),
                comment.getContent(),
                comment.getUserId().getValue(),
                comment.getDate());
        return ResponseEntity.ok(commentResponseResult);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteComment(@RequestHeader("authorization") String token, @PathVariable String id) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        DeleteComment deleteComment = new DeleteComment(id, userId);
        commandBus.send(deleteComment);
        return ResponseEntity.noContent().build();
    }

    //TODO show a the post and his comment ?
    //TODO share a comment ?
}
