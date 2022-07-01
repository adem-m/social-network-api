package com.esgi.modules.post.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.CoreUserMapper;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.authentication.application.DecodeTokenCommand;
import com.esgi.modules.authentication.domain.Token;
import com.esgi.modules.code.application.RetrieveCodeByPostId;
import com.esgi.modules.code.domain.Code;
import com.esgi.modules.code.exposition.CodeResponse;
import com.esgi.modules.post.application.*;
import com.esgi.modules.post.domain.FullPost;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostLike;
import com.esgi.modules.post.domain.PostLikeId;
import com.esgi.modules.user.application.RetrieveUserById;
import com.esgi.modules.user.domain.User;
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
@RequestMapping("/likePosts")
public class PostLikeController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public PostLikeController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> likePost(@RequestHeader(value = "authorization", required = false) String token,
                                         @RequestBody @Valid PostLikeRequest request) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        LikePost likePost = new LikePost(userId.getValue(), request.postId);
        PostLikeId postLikeId = (PostLikeId) commandBus.send(likePost);
        if (postLikeId == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.created(URI.create("/likePosts/id=" + postLikeId.getValue())).build();
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> unlike(@RequestHeader(value = "authorization", required = false) String token,
                                       @RequestBody @Valid PostLikeRequest request) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        UnlikePost unlikePost = new UnlikePost(userId.getValue(), request.postId);
        commandBus.send(unlikePost);
        return ResponseEntity.noContent().build();
    }
}
