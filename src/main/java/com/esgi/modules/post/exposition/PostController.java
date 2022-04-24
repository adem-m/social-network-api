package com.esgi.modules.post.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.code.application.RetrieveCodeByPostId;
import com.esgi.modules.code.domain.Code;
import com.esgi.modules.code.exposition.CodeResponse;
import com.esgi.modules.post.application.*;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public PostController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid PostRequest request) {
        CreatePost createPost = new CreatePost(request.content, request.code, request.userId);
        PostId postId = (PostId) commandBus.send(createPost);
        return ResponseEntity.created(URI.create("/post/" + postId.getValue())).build();
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostResponse> getPostById(@PathVariable String id) {
        final Post post = (Post) queryBus.send(new RetrievePostById(id));
        final Code code = (Code) queryBus.send(new RetrieveCodeByPostId(post.getId().getValue()));
        PostResponse postResponseResult = new PostResponse(String.valueOf(post.getId().getValue()), post.getContent(),
                new CodeResponse(String.valueOf(code.getCodeId().getValue()), code.getPostId().getValue(), code.getSource(), code.getLanguage()), post.getUserId().getValue(), post.getDate());
        return ResponseEntity.ok(postResponseResult);
    }

    @GetMapping(path = "/user={id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostsResponse> getAllPostsByUserId(@PathVariable String id) {
        final List<Post> posts = (List<Post>) queryBus.send(new RetrievePostsByUserId(id));
        PostsResponse postsResponseResult = new PostsResponse(new ArrayList<>());
        for (Post post : posts) {
            final Code code = (Code) queryBus.send(new RetrieveCodeByPostId(post.getId().getValue()));
            postsResponseResult.posts.add(new PostResponse(String.valueOf(post.getId().getValue()), post.getContent(),
                    new CodeResponse(String.valueOf(code.getCodeId().getValue()), code.getPostId().getValue(), code.getSource(), code.getLanguage()), String.valueOf(post.getUserId()), post.getDate()));
        }
        return ResponseEntity.ok(postsResponseResult);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostResponse> edit(@PathVariable String id, @RequestBody @Valid PostRequest request) {
        EditPost editPost = new EditPost(id, request.content);
        commandBus.send(editPost);
        final Post post = (Post) queryBus.send(new RetrievePostById(editPost.postId));
        final Code code = (Code) queryBus.send(new RetrieveCodeByPostId(post.getId().getValue()));
        PostResponse postResponseResult = new PostResponse(String.valueOf(post.getId().getValue()), post.getContent(),
                new CodeResponse(String.valueOf(code.getCodeId().getValue()), code.getPostId().getValue(), code.getSource(), code.getLanguage()), String.valueOf(post.getUserId()), post.getDate());
        return ResponseEntity.ok(postResponseResult);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        DeletePost deletePost = new DeletePost(id);
        commandBus.send(deletePost);
        return ResponseEntity.noContent().build();
    }

    //TODO share a post
    //TODO getAllByFollowingOrderByDate

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
