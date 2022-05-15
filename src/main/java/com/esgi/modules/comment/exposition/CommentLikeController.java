package com.esgi.modules.comment.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.authentication.application.DecodeTokenCommand;
import com.esgi.modules.authentication.domain.Token;
import com.esgi.modules.comment.application.LikeComment;
import com.esgi.modules.comment.application.RetrieveCommentById;
import com.esgi.modules.comment.application.RetrieveLikedCommentsByUserId;
import com.esgi.modules.comment.application.UnlikeComment;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentLike;
import com.esgi.modules.comment.domain.CommentLikeId;
import com.esgi.modules.user.domain.UserId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/likeComments")
public class CommentLikeController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public CommentLikeController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> likeComment(@RequestHeader("authorization") String token,
                                            @RequestBody @Valid CommentLikeRequest request) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        LikeComment likeComment = new LikeComment(userId.getValue(), request.commentId);
        CommentLikeId commentLikeId = (CommentLikeId) commandBus.send(likeComment);
        if (commentLikeId == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.created(URI.create("/likeComments/id=" + commentLikeId.getValue())).build();
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommentsResponse> getCommentsLikedByUserId(@RequestHeader("authorization") String token) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        final List<CommentLike> likedComments =
                (List<CommentLike>) queryBus.send(new RetrieveLikedCommentsByUserId(userId.getValue()));
        CommentsResponse commentsResponseResult = new CommentsResponse(new ArrayList<>());
        for (CommentLike commentLike : likedComments) {
            final Comment comment = (Comment) queryBus.send(new RetrieveCommentById(commentLike.getCommentId().getValue()));
            commentsResponseResult.comments.add(new CommentResponse(String.valueOf(comment.getCommentId().getValue()), String.valueOf(comment.getPostId().getValue()), comment.getContent(), comment.getUserId().getValue(), comment.getDate()));
        }
        return ResponseEntity.ok(commentsResponseResult);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> unlikeComment(@RequestHeader("authorization") String token,
                                              @RequestBody @Valid CommentLikeRequest request) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        UnlikeComment unlikeComment = new UnlikeComment(userId.getValue(), request.commentId);
        commandBus.send(unlikeComment);
        return ResponseEntity.noContent().build();
    }
}
