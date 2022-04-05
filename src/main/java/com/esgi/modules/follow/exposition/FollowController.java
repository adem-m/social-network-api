package com.esgi.modules.follow.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.follow.application.*;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.user.application.DeleteUser;
import com.esgi.modules.user.application.RetrieveUserById;
import com.esgi.modules.user.application.RetrieveUsers;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.exposition.EmailResponse;
import com.esgi.modules.user.exposition.UserResponse;
import com.esgi.modules.user.exposition.UsersResponse;
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
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
public class FollowController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public FollowController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping(path = "/follow", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createFollow(@RequestBody @Valid FollowRequest request) {
        CreateFollow createFollow = new CreateFollow(new UserId(request.followerId), new UserId(request.followedId));
        commandBus.send(createFollow);
        return ResponseEntity.ok().build();
    }

    /*@GetMapping(path = "/following/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FollowsResponse> getFollowingByFollowerId(@PathVariable int id){
        final List<Follow> follows = (List<Follow>) queryBus.send(new RetrieveFollowing(id));
        FollowsResponse followsResponseResult = new FollowsResponse(follows.stream().map(follow -> new FollowResponse(String.valueOf(follow.getFollowId().getValue()), follow.getFollowerId(), follow.getFollowedId())).collect(Collectors.toList()));
        return ResponseEntity.ok(followsResponseResult);
    }*/

    @GetMapping(path = "/following/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UsersResponse> getFollowingByFollowerId(@PathVariable int id) {
        final List<Follow> follows = (List<Follow>) queryBus.send(new RetrieveFollowing(id));
        return getUsersResponseResponseEntity(follows);
    }

    /*@GetMapping(path = "/followers/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FollowsResponse> getFollowersByFollowedId(@PathVariable int id){
        final List<Follow> follows = (List<Follow>) queryBus.send(new RetrieveFollowers(id));
        FollowsResponse followsResponseResult = new FollowsResponse(follows.stream().map(follow -> new FollowResponse(String.valueOf(follow.getFollowId().getValue()), follow.getFollowerId(), follow.getFollowedId())).collect(Collectors.toList()));
        return ResponseEntity.ok(followsResponseResult);
    }*/

    @GetMapping(path = "/followers/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UsersResponse> getFollowersByFollowerId(@PathVariable int id) {
        final List<Follow> follows = (List<Follow>) queryBus.send(new RetrieveFollowers(id));
        return getUsersResponseResponseEntity(follows);
    }

    private ResponseEntity<UsersResponse> getUsersResponseResponseEntity(List<Follow> follows) {
        UsersResponse usersResponseResult = new UsersResponse(new ArrayList<>());
        for (Follow follow : follows) {
            final User user = (User) queryBus.send(new RetrieveUserById(follow.getFollowedId().getValue()));
            usersResponseResult.members.add(new UserResponse(String.valueOf(user.getId().getValue()), user.getLastname(), user.getFirstname(), new EmailResponse(user.getEmail().getEmail()), user.getPassword()));
        }
        return ResponseEntity.ok(usersResponseResult);
    }

    @GetMapping(path = "/follows", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FollowsResponse> getAllFollows() {
        final List<Follow> follows = (List<Follow>) queryBus.send(new RetrieveFollows());
        FollowsResponse followsResponseResult = new FollowsResponse(follows.stream().map(follow -> new FollowResponse(String.valueOf(follow.getFollowId().getValue()), follow.getFollowerId(), follow.getFollowedId())).collect(Collectors.toList()));
        return ResponseEntity.ok(followsResponseResult);
    }

    @DeleteMapping(path= "/unfollow/{id}")
    public Map<String, Boolean> unfollow(@PathVariable int id) {
        Unfollow unfollow = new Unfollow(id);
        commandBus.send(unfollow);
        Map<String, Boolean> response = new HashMap<>();
        response.put("unfollowed", Boolean.TRUE);
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
