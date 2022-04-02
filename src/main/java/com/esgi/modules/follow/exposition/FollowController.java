package com.esgi.modules.follow.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.follow.application.CreateFollow;
import com.esgi.modules.follow.application.RetrieveFollowers;
import com.esgi.modules.follow.application.RetrieveFollowing;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.user.application.RetrieveUserById;
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
        CreateFollow createFollow = new CreateFollow(request.followerId, request.followedId);
        commandBus.send(createFollow);
        return ResponseEntity.ok().build();
    }

    /*@GetMapping(path = "/following", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FollowsResponse> getFollowingByFollowerId(UserId id){
        final List<Follow> follows = (List<Follow>) queryBus.send(new RetrieveFollowing(id));
        FollowsResponse followsResponseResult = new FollowsResponse(follows.stream().map(follow -> new FollowResponse(String.valueOf(follow.getFollowId().getValue()), follow.getFollowerId(), follow.getFollowedId())).collect(Collectors.toList()));
        return ResponseEntity.ok(followsResponseResult);
    }*/

    @GetMapping(path = "/following", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UsersResponse> getFollowingByFollowerId(UserId id) {
        final List<Follow> follows = (List<Follow>) queryBus.send(new RetrieveFollowing(id));
        return getUsersResponseResponseEntity(follows);
    }

    /*@GetMapping(path = "/followers", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FollowsResponse> getFollowersByFollowedId(UserId id){
        final List<Follow> follows = (List<Follow>) queryBus.send(new RetrieveFollowers(id));
        FollowsResponse followsResponseResult = new FollowsResponse(follows.stream().map(follow -> new FollowResponse(String.valueOf(follow.getFollowId().getValue()), follow.getFollowerId(), follow.getFollowedId())).collect(Collectors.toList()));
        return ResponseEntity.ok(followsResponseResult);
    }*/

    @GetMapping(path = "/followers", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UsersResponse> getFollowersByFollowerId(UserId id) {
        final List<Follow> follows = (List<Follow>) queryBus.send(new RetrieveFollowers(id));
        return getUsersResponseResponseEntity(follows);
    }

    private ResponseEntity<UsersResponse> getUsersResponseResponseEntity(List<Follow> follows) {
        UsersResponse usersResponseResult = new UsersResponse(new ArrayList<>());
        for (Follow follow : follows) {
            final User user = (User) queryBus.send(new RetrieveUserById(follow.getFollowedId()));
            usersResponseResult.members.add(new UserResponse(String.valueOf(user.getId().getValue()), user.getLastname(), user.getFirstname(), new EmailResponse(user.getEmail().getEmail()), user.getPassword()));
        }
        return ResponseEntity.ok(usersResponseResult);
    }

    //TODO unfollow an user

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
    }}
