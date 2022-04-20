package com.esgi.modules.post.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.code.application.RetrieveCodeByPostId;
import com.esgi.modules.code.domain.Code;
import com.esgi.modules.code.exposition.CodeResponse;
import com.esgi.modules.post.application.*;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostLike;
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
public class PostLikeController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public PostLikeController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping(path = "/likePost", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> likePost(@RequestBody @Valid PostLikeRequest request) {
        LikePost likePost = new LikePost(request.userId, request.postId);
        commandBus.send(likePost);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/likedPosts/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostsResponse> getAllPostsLikedByUserId(@PathVariable int id) {
        final List<PostLike> likedPosts = (List<PostLike>) queryBus.send(new RetrieveLikedPostsByUserId(id));
        PostsResponse postsResponseResult = new PostsResponse(new ArrayList<>());
        for (PostLike postLike : likedPosts) {
            final Post post = (Post) queryBus.send(new RetrievePostById(postLike.getPostId().getValue()));
            final Code code = (Code) queryBus.send(new RetrieveCodeByPostId(post.getId().getValue()));
            postsResponseResult.posts.add(new PostResponse(String.valueOf(post.getId().getValue()), post.getContent(),
                    new CodeResponse(String.valueOf(code.getCodeId().getValue()), code.getPostId(), code.getSource(), code.getLanguage()), String.valueOf(post.getUserId()),post.getDate()));
        }
        return ResponseEntity.ok(postsResponseResult);
    }

    @DeleteMapping(path= "/unlikePost", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Boolean> unlike(@RequestBody @Valid PostLikeRequest request) {
        UnlikePost unlikePost = new UnlikePost(request.userId, request.postId);
        commandBus.send(unlikePost);
        Map<String, Boolean> response = new HashMap<>();
        response.put("unlike", Boolean.TRUE);
        return response;
    }

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
