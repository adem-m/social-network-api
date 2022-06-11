package com.esgi.modules.comment.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.CoreUserMapper;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.authentication.application.DecodeTokenCommand;
import com.esgi.modules.authentication.domain.Token;
import com.esgi.modules.comment.application.*;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.comment.domain.FullComment;
import com.esgi.modules.user.application.RetrieveUserById;
import com.esgi.modules.user.domain.User;
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
    public ResponseEntity<Void> create(@RequestHeader(value = "authorization", required = false) String token,
                                       @RequestBody @Valid CommentRequest request) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        CreateComment createComment = new CreateComment(request.postId, request.content, userId.getValue());
        CommentId commentId = (CommentId) commandBus.send(createComment);
        return ResponseEntity.created(URI.create("/comments/id=" + commentId.getValue())).build();
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentResponse> getCommentById(
            @RequestHeader(value = "authorization", required = false) String token,
            @PathVariable String id) {
        boolean isLiked = false;
        final FullComment fullComment = (FullComment) queryBus.send(new RetrieveCommentById(id));
        final Comment comment = fullComment.comment();
        if (token != null) {
            UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
            isLiked = (Boolean) queryBus.send(new IsCommentLikedQuery(comment.id().getValue(), userId.getValue()));
        }
        final User user = (User) queryBus.send(new RetrieveUserById(comment.getUserId().getValue()));
        CommentResponse commentResponseResult = new CommentResponse(
                String.valueOf(comment.getCommentId().getValue()),
                String.valueOf(comment.getPostId().getValue()),
                comment.getContent(),
                CoreUserMapper.map(user),
                comment.getDate().toString(),
                fullComment.likes(),
                isLiked);
        return ResponseEntity.ok(commentResponseResult);
    }

    @GetMapping(path = "/user={id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentsResponse> getCommentsByUserId(
            @RequestHeader(value = "authorization", required = false) String token,
            @PathVariable String id) {
        final List<FullComment> comments = (List<FullComment>) queryBus.send(new RetrieveComments(id));
        return getCommentsResponseResponseEntity(token, comments);
    }

    @GetMapping(path = "/post={id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentsResponse> getCommentsByPostId(
            @RequestHeader(value = "authorization", required = false) String token,
            @PathVariable String id) {
        final List<FullComment> comments = (List<FullComment>) queryBus.send(new RetrieveCommentsByPostId(id));
        return getCommentsResponseResponseEntity(token, comments);
    }

    private ResponseEntity<CommentsResponse> getCommentsResponseResponseEntity(String token, List<FullComment> comments) {
        CommentsResponse commentsResponseResult = new CommentsResponse(
                comments.stream().map(fullComment -> {
                    boolean isLiked = false;
                    final Comment comment = fullComment.comment();
                    if (token != null) {
                        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
                        isLiked = (Boolean) queryBus.send(new IsCommentLikedQuery(comment.id().getValue(), userId.getValue()));
                    }
                    final User user = (User) queryBus.send(new RetrieveUserById(comment.getUserId().getValue()));
                    return new CommentResponse(
                            comment.getCommentId().getValue(),
                            comment.getUserId().getValue(),
                            comment.getContent(),
                            CoreUserMapper.map(user),
                            comment.getDate().toString(),
                            fullComment.likes(),
                            isLiked);
                }).collect(Collectors.toList()));
        return ResponseEntity.ok(commentsResponseResult);
    }

//    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<CommentResponse> edit(@PathVariable String id, @RequestBody @Valid EditCommentRequest request) {
//        EditComment editComment = new EditComment(id, request.content);
//        commandBus.send(editComment);
//        final FullComment fullComment = (FullComment) queryBus.send(new RetrieveCommentById(editComment.commentId));
//        final Comment comment = fullComment.comment();
//        final User user = (User) queryBus.send(new RetrieveUserById(comment.getUserId().getValue()));
//        CommentResponse commentResponseResult = new CommentResponse(
//                comment.getCommentId().getValue(),
//                comment.getUserId().getValue(),
//                comment.getContent(),
//                CoreUserMapper.map(user),
//                comment.getDate().toString(),
//                fullComment.likes(),
//                fullComment.isLiked());
//        return ResponseEntity.ok(commentResponseResult);
//    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteComment(
            @RequestHeader(value = "authorization", required = false) String token,
            @PathVariable String id) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        DeleteComment deleteComment = new DeleteComment(id, userId);
        commandBus.send(deleteComment);
        return ResponseEntity.noContent().build();
    }

    //TODO show a the post and his comment ?
    //TODO share a comment ?
}
