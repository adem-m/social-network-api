package com.esgi.modules.comment.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.comment.application.LikeComment;
import com.esgi.modules.comment.application.RetrieveLikedCommentsByUserId;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentLike;
import com.esgi.modules.user.domain.UserId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@RestController
public class CommentLikeController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public CommentLikeController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping(path = "/likeComment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> likeComment(@RequestBody @Valid CommentLikeRequest request) {
        LikeComment likeComment = new LikeComment(request.userId, request.commentId);
        commandBus.send(likeComment);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/likedComments/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentsResponse> getAllCommentsLikedByUserId(@PathVariable UserId id) {
        final List<CommentLike> likedComments = (List<CommentLike>) queryBus.send(new RetrieveLikedCommentsByUserId(id));
        CommentsResponse commentsResponseResult = new CommentsResponse(new ArrayList<>());
        for (CommentLike commentLike : likedComments) {
            final Comment comment = (Comment) queryBus.send(new RetrieveLikedCommentsByUserId(commentLike.getUserId()));
            commentsResponseResult.comments.add(new CommentResponse(String.valueOf(comment.getCommentId().getValue()), String.valueOf(comment.getPostId().getValue()), comment.getContent(), comment.getUserId(), comment.getDate()));
        }
        return ResponseEntity.ok(commentsResponseResult);
    }

    //TODO unlike a comment (DeleteMapping)

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
