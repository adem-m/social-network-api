package com.esgi.modules.friendship.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.friendship.application.AddFriendship;
import com.esgi.modules.friendship.application.RetrieveFriends;
import com.esgi.modules.friendship.domain.Friendship;
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

    @PostMapping(path = "/addFriend", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addFriend(@RequestBody @Valid FriendshipRequest request) {
        AddFriendship addFriendship = new AddFriendship(request.userId1, request.userId2);
        commandBus.send(addFriendship);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/friends{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FriendshipResponse> getFriendshipById(UserId id){
        final Friendship friendship = queryBus.send(new RetrieveFriends(id));
        FriendshipResponse friendshipResponseResult = new FriendshipResponse(String.valueOf(friendship.getFriendshipId().getValue()), friendship.getUserId1(), friendship.getUserId2());
        return ResponseEntity.ok(friendshipResponseResult);
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
