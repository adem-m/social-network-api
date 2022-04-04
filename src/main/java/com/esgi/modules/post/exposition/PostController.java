package com.esgi.modules.post.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.post.application.CreatePost;
import com.esgi.modules.post.application.RetrievePostById;
import com.esgi.modules.post.application.RetrievePostsByUserId;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
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
public class PostController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public PostController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping(path = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid PostRequest request) {
        CreatePost createPost = new CreatePost(request.content, request.userId, request.date);
        commandBus.send(createPost);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/post/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostResponse> getPostById(@PathVariable PostId id){
        final Post post = (Post) queryBus.send(new RetrievePostById(id));
        PostResponse postResponseResult = new PostResponse(String.valueOf(post.getId().getValue()), post.getContent(), post.getUserId(),post.getDate());
        return ResponseEntity.ok(postResponseResult);
    }

    @GetMapping(path = "/posts/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostsResponse> getAllPostsByUserId(@PathVariable UserId id) {
        final List<Post> posts = (List<Post>) queryBus.send(new RetrievePostsByUserId(id));
        PostsResponse postsResponseResult = new PostsResponse(posts.stream().map(post -> new PostResponse(String.valueOf(post.getId().getValue()), post.getContent(), post.getUserId(), post.getDate())).collect(Collectors.toList()));
        return ResponseEntity.ok(postsResponseResult);
    }

    /*@PutMapping(path = "/post/{id}/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> edit(@PathVariable PostId id, @RequestBody @Valid PostRequest request) {
    }*/

    //TODO share a post
    //TODO delete a post
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
