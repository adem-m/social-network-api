package com.esgi.modules.post.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.authentication.application.DecodeTokenCommand;
import com.esgi.modules.authentication.domain.Token;
import com.esgi.modules.code.application.RetrieveCodeByPostId;
import com.esgi.modules.code.domain.Code;
import com.esgi.modules.code.exposition.CodeResponse;
import com.esgi.modules.post.application.*;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<Void> create(@RequestHeader("authorization") String token,
                                       @RequestBody @Valid PostRequest request) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        CreatePost createPost = new CreatePost(request.content, request.code, userId.getValue());
        PostId postId = (PostId) commandBus.send(createPost);
        return ResponseEntity.created(URI.create("/posts/" + postId.getValue())).build();
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostResponse> getPostById(@PathVariable String id) {
        final Post post = (Post) queryBus.send(new RetrievePostById(id));
        final Code code = (Code) queryBus.send(new RetrieveCodeByPostId(post.getId().getValue()));
        PostResponse postResponseResult = new PostResponse(
                String.valueOf(post.getId().getValue()),
                post.getContent(),
                new CodeResponse(
                        String.valueOf(code.getCodeId().getValue()),
                        code.getPostId().getValue(),
                        code.getSource(),
                        code.getLanguage()),
                post.getUserId().getValue(),
                post.getDate());
        return ResponseEntity.ok(postResponseResult);
    }

    @GetMapping(path = "/user={id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostsResponse> getAllPostsByUserId(@PathVariable String id) {
        final List<Post> posts = (List<Post>) queryBus.send(new RetrievePostsByUserId(id));
        return getPostsResponseResponseEntity(posts);
    }

    @GetMapping(path = "/feed", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostsResponse> getFeed(@RequestHeader("authorization") String token) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        final List<Post> posts = (List<Post>) queryBus.send(new RetrieveFeedByUserId(userId.getValue()));
        return getPostsResponseResponseEntity(posts);
    }

    private ResponseEntity<PostsResponse> getPostsResponseResponseEntity(List<Post> posts) {
        PostsResponse postsResponseResult = new PostsResponse(new ArrayList<>());
        for (Post post : posts) {
            final Code code = (Code) queryBus.send(new RetrieveCodeByPostId(post.getId().getValue()));
            postsResponseResult.posts.add(new PostResponse(
                    post.getId().getValue(),
                    post.getContent(),
                    new CodeResponse(
                            String.valueOf(code.getCodeId().getValue()),
                            code.getPostId().getValue(),
                            code.getSource(),
                            code.getLanguage()),
                    post.getUserId().getValue(),
                    post.getDate()));
        }
        return ResponseEntity.ok(postsResponseResult);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PostResponse> edit(@RequestHeader("authorization") String token,
                                             @PathVariable String id,
                                             @RequestBody @Valid EditPostRequest request) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        EditPost editPost = new EditPost(id, request.content, request.code, userId.getValue());
        commandBus.send(editPost);
        final Post post = (Post) queryBus.send(new RetrievePostById(editPost.postId));
        final Code code = (Code) queryBus.send(new RetrieveCodeByPostId(post.getId().getValue()));
        PostResponse postResponseResult = new PostResponse(
                String.valueOf(post.getId().getValue()),
                post.getContent(),
                new CodeResponse(
                        String.valueOf(code.getCodeId().getValue()),
                        code.getPostId().getValue(),
                        code.getSource(),
                        code.getLanguage()),
                post.getUserId().getValue(),
                post.getDate());
        return ResponseEntity.ok(postResponseResult);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePost(@RequestHeader("authorization") String token,
                                           @PathVariable String id) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        DeletePost deletePost = new DeletePost(id, userId.getValue());
        commandBus.send(deletePost);
        return ResponseEntity.noContent().build();
    }

    //TODO share a post ?
}
