package com.esgi.modules.comment.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.comment.application.CreateComment;
import com.esgi.modules.comment.application.RetrieveCommentById;
import com.esgi.modules.comment.application.RetrieveComments;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentId;
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
        CreateComment createComment = new CreateComment(request.postId ,request.content, request.userId, request.date);
        commandBus.send(createComment);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/comment/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentResponse> getCommentById(CommentId id){
        final Comment comment = (Comment) queryBus.send(new RetrieveCommentById(id));
        CommentResponse commentResponseResult = new CommentResponse(String.valueOf(comment.getCommentId().getValue()), String.valueOf(comment.getPostId().getValue()), comment.getContent(), comment.getUserId(), comment.getDate());
        return ResponseEntity.ok(commentResponseResult);
    }

    @GetMapping(path = "/comments", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentsResponse> getAllCommentsByUserId(UserId id) {
        final List<Comment> comments = (List<Comment>) queryBus.send(new RetrieveComments(id));
        CommentsResponse commentsResponseResult = new CommentsResponse(comments.stream().map(comment -> new CommentResponse(String.valueOf(comment.getCommentId().getValue()), String.valueOf(comment.getUserId().getValue()), comment.getContent(), comment.getUserId(), comment.getDate())).collect(Collectors.toList()));
        return ResponseEntity.ok(commentsResponseResult);
    }

    /*@PostMapping(path = "/comment/{id}/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> edit(@RequestBody @Valid CommentRequest request) {
        EditComment editComment = new EditComment(request.postId, request.content, request.userId, request.date);
        commandBus.send(editComment);
        return ResponseEntity.ok().build();
    }*/

    //TODO show a the post and the comment (get)
    //TODO share a comment ?
    //TODO delete a comment (DeleteMapping)

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
