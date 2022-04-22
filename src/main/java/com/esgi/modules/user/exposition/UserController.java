package com.esgi.modules.user.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.user.application.*;
import com.esgi.modules.user.domain.Email;
import com.esgi.modules.user.domain.User;
import com.esgi.modules.user.domain.UserId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody @Valid UserRequest request) {
        CreateUser createUser = new CreateUser(request.lastname, request.firstname, new Email(request.email), request.password);
        UserId userId = (UserId) commandBus.send(createUser);
        return ResponseEntity.created(URI.create("/users/id=" + userId.getValue())).build();
    }

    @GetMapping(path = "/id={id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        final User user = (User) queryBus.send(new RetrieveUserById(id));
        UserResponse userResponseResult = new UserResponse(String.valueOf(user.getId().getValue()), user.getLastname(), user.getFirstname(), new EmailResponse(user.getEmail().getEmail()), user.getPassword());
        return ResponseEntity.ok(userResponseResult);
    }

    @GetMapping(path = "/email={email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        final User user = (User) queryBus.send(new RetrieveUserByEmail(email));
        UserResponse userResponseResult = new UserResponse(String.valueOf(user.getId().getValue()), user.getLastname(), user.getFirstname(), new EmailResponse(user.getEmail().getEmail()), user.getPassword());
        return ResponseEntity.ok(userResponseResult);
    }

    @GetMapping(path = "/{name}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UsersResponse> getUserByName(@PathVariable String name) {
        final List<User> users = (List<User>) queryBus.send(new RetrieveUsersByName(name));
        UsersResponse usersResponseResult = new UsersResponse(users.stream().map(user -> new UserResponse(String.valueOf(user.getId().getValue()), user.getLastname(), user.getFirstname(), new EmailResponse(user.getEmail().getEmail()), user.getPassword())).collect(Collectors.toList()));
        return ResponseEntity.ok(usersResponseResult);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UsersResponse> getAllUsers() {
        final List<User> users = (List<User>) queryBus.send(new RetrieveUsers());
        UsersResponse usersResponseResult = new UsersResponse(users.stream().map(user -> new UserResponse(String.valueOf(user.getId().getValue()), user.getLastname(), user.getFirstname(), new EmailResponse(user.getEmail().getEmail()), user.getPassword())).collect(Collectors.toList()));
        return ResponseEntity.ok(usersResponseResult);
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody @Valid UpdateUserRequest request) {
        UpdateUser updateUser = new UpdateUser(id, new Email(request.email), request.password);
        commandBus.send(updateUser);
        final User user = (User) queryBus.send(new RetrieveUserById(updateUser.userId));
        UserResponse userResponseResult = new UserResponse(String.valueOf(user.getId().getValue()), user.getLastname(), user.getFirstname(), new EmailResponse(user.getEmail().getEmail()), user.getPassword());
        return ResponseEntity.ok(userResponseResult);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        DeleteUser deleteUser = new DeleteUser(id);
        commandBus.send(deleteUser);
        return ResponseEntity.noContent().build();
    }

    //TODO getUserByToken
    //TODO block an user ?

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