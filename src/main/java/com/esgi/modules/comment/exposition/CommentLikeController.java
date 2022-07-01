package com.esgi.modules.comment.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.authentication.application.DecodeTokenCommand;
import com.esgi.modules.authentication.domain.Token;
import com.esgi.modules.comment.application.LikeComment;
import com.esgi.modules.comment.application.UnlikeComment;
import com.esgi.modules.comment.domain.CommentLikeId;
import com.esgi.modules.user.domain.UserId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

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
    public ResponseEntity<Void> likeComment(@RequestHeader(value = "authorization", required = false) String token,
                                            @RequestBody @Valid CommentLikeRequest request) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        LikeComment likeComment = new LikeComment(userId.getValue(), request.commentId);
        CommentLikeId commentLikeId = (CommentLikeId) commandBus.send(likeComment);
        if (commentLikeId == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.created(URI.create("/likeComments/id=" + commentLikeId.getValue())).build();
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> unlikeComment(@RequestHeader(value = "authorization", required = false) String token,
                                              @RequestBody @Valid CommentLikeRequest request) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        UnlikeComment unlikeComment = new UnlikeComment(userId.getValue(), request.commentId);
        commandBus.send(unlikeComment);
        return ResponseEntity.noContent().build();
    }
}
