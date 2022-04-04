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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
@RestController
public class UserController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public UserController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody @Valid UserRequest request) {
        CreateUser createUser = new CreateUser(request.lastname, request.firstname, new Email(request.email.email), request.password);
        commandBus.send(createUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/user/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> getUserById(@PathVariable int id){
        final UserId userId = new UserId(id);
        final User user = (User) queryBus.send(new RetrieveUserById(userId));
        UserResponse userResponseResult = new UserResponse(String.valueOf(user.getId().getValue()), user.getLastname(), user.getFirstname(),new EmailResponse(user.getEmail().getEmail()),user.getPassword());
        return ResponseEntity.ok(userResponseResult);
    }

    @GetMapping(path = "/users/{name}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UsersResponse> getUserByName(@PathVariable String name){
        final List<User> users = (List<User>) queryBus.send(new RetrieveUsersByName(name));
        UsersResponse usersResponseResult = new UsersResponse(users.stream().map(user -> new UserResponse(String.valueOf(user.getId().getValue()), user.getLastname(), user.getFirstname(),new EmailResponse(user.getEmail().getEmail()),user.getPassword())).collect(Collectors.toList()));
        return ResponseEntity.ok(usersResponseResult);
    }

    @GetMapping(path = "/users", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UsersResponse> getAllUsers() {
        final List<User> users = (List<User>) queryBus.send(new RetrieveUsers());
        UsersResponse usersResponseResult = new UsersResponse(users.stream().map(user -> new UserResponse(String.valueOf(user.getId().getValue()), user.getLastname(), user.getFirstname(),new EmailResponse(user.getEmail().getEmail()),user.getPassword())).collect(Collectors.toList()));
        return ResponseEntity.ok(usersResponseResult);
    }

    @PutMapping(path = "/user/{id}/update", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> updateUser(@PathVariable int id, @RequestBody @Valid UserRequest request) {
        UpdateUser updateUser = new UpdateUser(id, new Email(request.email.email));
        commandBus.send(updateUser);
        final User user = (User) queryBus.send(new RetrieveUserById(new UserId(updateUser.userId)));
        UserResponse userResponseResult = new UserResponse(String.valueOf(user.getId().getValue()), user.getLastname(), user.getFirstname(),new EmailResponse(user.getEmail().getEmail()),user.getPassword());
        return ResponseEntity.ok(userResponseResult);
    }

    @DeleteMapping(path= "/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable int id) {
        DeleteUser deleteUser = new DeleteUser(id);
        commandBus.send(deleteUser);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
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