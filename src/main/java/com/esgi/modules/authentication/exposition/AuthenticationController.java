package com.esgi.modules.authentication.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.modules.authentication.application.LoginCommand;
import com.esgi.modules.authentication.application.WrongPasswordException;
import com.esgi.modules.authentication.domain.Email;
import com.esgi.modules.authentication.domain.Password;
import com.esgi.modules.authentication.domain.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@Slf4j
public class AuthenticationController {
    private final CommandBus commandBus;

    public AuthenticationController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        LoginCommand loginCommand = new LoginCommand(new Email(loginRequest.email), new Password(loginRequest.password));
        Token token = (Token) commandBus.send(loginCommand);
        return ResponseEntity.ok(new LoginResponse(token.value()));
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<Map<String, String>> on(WrongPasswordException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }
}
