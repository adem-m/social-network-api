package com.esgi.modules.user.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.authentication.application.DecodeTokenCommand;
import com.esgi.modules.authentication.domain.Token;
import com.esgi.modules.user.application.*;
import com.esgi.modules.user.domain.Email;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/users")
public class UserController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public UserController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Void> register(@RequestPart(required = false) MultipartFile image,
                                         @RequestParam String firstname,
                                         @RequestParam String lastname,
                                         @RequestParam String email,
                                         @RequestParam String password) throws IOException {
        CreateUser createUser = new CreateUser(
                lastname,
                firstname,
                new Email(email),
                password,
                image);
        UserId userId = (UserId) commandBus.send(createUser);
        return ResponseEntity.created(URI.create("/users/id=" + userId.getValue())).build();
    }

    @GetMapping(path = "/self", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GetSelfResponse> getSelf(@RequestHeader(value = "authorization", required = false) String token) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        final User user = (User) queryBus.send(new RetrieveUserById(userId.getValue()));
        final GetSelfResponse response = new GetSelfResponse(user.getId().getValue());
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/id={id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> getUserById(@RequestHeader(value = "authorization", required = false) String token,
                                                    @PathVariable String id) {
        UserId userId = new UserId(null);
        if (token != null) {
            userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        }
        final User user = (User) queryBus.send(new RetrieveUserById(id, userId.getValue()));
        UserResponse userResponseResult =
                new UserResponse(
                        String.valueOf(user.getId().getValue()),
                        user.getLastname(),
                        user.getFirstname(),
                        user.getEmail().getEmail(),
                        user.isFollowed());
        return ResponseEntity.ok(userResponseResult);
    }

    @GetMapping(path = "/email={email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        final User user = (User) queryBus.send(new RetrieveUserByEmail(email));
        UserResponse userResponseResult =
                new UserResponse(
                        String.valueOf(user.getId().getValue()),
                        user.getLastname(),
                        user.getFirstname(),
                        user.getEmail().getEmail());
        return ResponseEntity.ok(userResponseResult);
    }

    @GetMapping(path = "/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UsersResponse> getUserByName(@PathVariable String name) {
        final List<User> users = (List<User>) queryBus.send(new RetrieveUsersByName(name));
        UsersResponse usersResponseResult =
                new UsersResponse(users.stream().map(user ->
                        new UserResponse(
                                String.valueOf(user.getId().getValue()),
                                user.getLastname(),
                                user.getFirstname(),
                                user.getEmail().getEmail())).collect(Collectors.toList()));
        return ResponseEntity.ok(usersResponseResult);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UsersResponse> getAllUsers() {
        final List<User> users = (List<User>) queryBus.send(new RetrieveUsers());
        UsersResponse usersResponseResult =
                new UsersResponse(users.stream().map(user ->
                        new UserResponse(
                                String.valueOf(user.getId().getValue()),
                                user.getLastname(),
                                user.getFirstname(),
                                user.getEmail().getEmail())).collect(Collectors.toList()));
        return ResponseEntity.ok(usersResponseResult);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> updateUser(@RequestHeader(value = "authorization", required = false) String token,
                                                   @RequestBody @Valid UpdateUserRequest request) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        UpdateUser updateUser = new UpdateUser(userId.getValue(), new Email(request.email), request.password);
        commandBus.send(updateUser);
        final User user = (User) queryBus.send(new RetrieveUserById(updateUser.userId));
        UserResponse userResponseResult =
                new UserResponse(
                        String.valueOf(user.getId().getValue()),
                        user.getLastname(),
                        user.getFirstname(),
                        user.getEmail().getEmail());
        return ResponseEntity.ok(userResponseResult);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestHeader(value = "authorization", required = false) String token) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        DeleteUser deleteUser = new DeleteUser(userId.getValue());
        commandBus.send(deleteUser);
        return ResponseEntity.noContent().build();
    }

    //TODO getUserByToken
    //TODO block an user ?
}