package com.esgi.modules.challenge.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.modules.authentication.application.DecodeTokenCommand;
import com.esgi.modules.authentication.domain.Token;
import com.esgi.modules.challenge.application.AddChallengeEntryCommand;
import com.esgi.modules.challenge.application.DeleteChallengeEntryCommand;
import com.esgi.modules.challenge.domain.ChallengeEntryId;
import com.esgi.modules.user.domain.UserId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {
    private final CommandBus commandBus;

    public ChallengeController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }


    @PostMapping(value = "/add-to-queue", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addToQueue(@RequestHeader("authorization") String token,
                                           @RequestBody @Valid AddToQueueRequest request) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        ChallengeEntryId challengeEntryId = (ChallengeEntryId)
                commandBus.send(new AddChallengeEntryCommand(userId.getValue(), request.codeId));
        return ResponseEntity.created(URI.create("/challenge/id=" + challengeEntryId.value())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChallengeEntry(@RequestHeader("authorization") String token,
                                                     @PathVariable String id) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        commandBus.send(new DeleteChallengeEntryCommand(userId.getValue(), id));
        return ResponseEntity.noContent().build();
    }
}
