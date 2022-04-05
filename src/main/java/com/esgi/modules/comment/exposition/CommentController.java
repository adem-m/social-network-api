package com.esgi.modules.comment.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.comment.application.*;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.post.application.DeletePost;
import com.esgi.modules.post.application.EditPost;
import com.esgi.modules.post.application.RetrievePostById;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.exposition.PostRequest;
import com.esgi.modules.post.exposition.PostResponse;
import com.esgi.modules.user.domain.UserId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
public class CommentController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public CommentController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping(path = "/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid CommentRequest request) {
        CreateComment createComment = new CreateComment(request.postId ,request.content, request.userId);
        commandBus.send(createComment);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/comment/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable int id){
        final Comment comment = (Comment) queryBus.send(new RetrieveCommentById(id));
        CommentResponse commentResponseResult = new CommentResponse(String.valueOf(comment.getCommentId().getValue()), String.valueOf(comment.getPostId().getValue()), comment.getContent(), comment.getUserId(), comment.getDate());
        return ResponseEntity.ok(commentResponseResult);
    }

    @GetMapping(path = "/comments/user={id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentsResponse> getCommentsByUserId(@PathVariable int id) {
        final List<Comment> comments = (List<Comment>) queryBus.send(new RetrieveComments(id));
        CommentsResponse commentsResponseResult = new CommentsResponse(comments.stream().map(comment -> new CommentResponse(String.valueOf(comment.getCommentId().getValue()), String.valueOf(comment.getUserId().getValue()), comment.getContent(), comment.getUserId(), comment.getDate())).collect(Collectors.toList()));
        return ResponseEntity.ok(commentsResponseResult);
    }

    @GetMapping(path = "/comments/post={id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentsResponse> getCommentsByPostId(@PathVariable int id) {
        final List<Comment> comments = (List<Comment>) queryBus.send(new RetrieveCommentsByPostId(id));
        CommentsResponse commentsResponseResult = new CommentsResponse(comments.stream().map(comment -> new CommentResponse(String.valueOf(comment.getCommentId().getValue()), String.valueOf(comment.getUserId().getValue()), comment.getContent(), comment.getUserId(), comment.getDate())).collect(Collectors.toList()));
        return ResponseEntity.ok(commentsResponseResult);
    }

    @PutMapping(path = "/comment/{id}/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentResponse> edit(@PathVariable int id, @RequestBody @Valid CommentRequest request) {
        EditComment editComment = new EditComment(id, request.content);
        commandBus.send(editComment);
        final Comment comment = (Comment) queryBus.send(new RetrieveCommentById(editComment.commentId));
        CommentResponse commentResponseResult = new CommentResponse(String.valueOf(comment.getCommentId().getValue()), String.valueOf(comment.getPostId().getValue()), comment.getContent(), comment.getUserId(), comment.getDate());
        return ResponseEntity.ok(commentResponseResult);
    }

    @DeleteMapping(path= "/comment/{id}")
    public Map<String, Boolean> deleteComment(@PathVariable int id) {
        DeleteComment deleteComment = new DeleteComment(id);
        commandBus.send(deleteComment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    //TODO show a the post and his comment (get)
    //TODO share a comment ?

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
