package com.esgi.modules.follow.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.follow.application.CreateFollow;
import com.esgi.modules.follow.application.RetrieveFollows;
import com.esgi.modules.follow.domain.Follow;
import com.esgi.modules.user.domain.UserId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@RestController
public class FriendshipController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public FriendshipController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping(path = "/follow", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createFollow(@RequestBody @Valid FollowRequest request) {
        CreateFollow createFollow = new CreateFollow(request.followerId, request.followedId);
        commandBus.send(createFollow);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/following", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FollowResponse> getFollowingByFollowerId(UserId id){
        final Follow follow = (Follow) queryBus.send(new RetrieveFollows(id));
        FollowResponse followResponseResult = new FollowResponse(String.valueOf(follow.getFollowId().getValue()), follow.getFollowerId(), follow.getFollowedId());
        return ResponseEntity.ok(followResponseResult);
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
    }}
