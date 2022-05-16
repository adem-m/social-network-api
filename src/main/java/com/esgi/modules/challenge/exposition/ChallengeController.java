package com.esgi.modules.challenge.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.authentication.application.DecodeTokenCommand;
import com.esgi.modules.authentication.domain.Token;
import com.esgi.modules.challenge.application.AddChallengeEntryCommand;
import com.esgi.modules.challenge.application.DeleteChallengeEntryCommand;
import com.esgi.modules.challenge.application.RunChallengeQuery;
import com.esgi.modules.challenge.domain.ChallengeEntryId;
import com.esgi.modules.challenge.domain.ChallengeOutput;
import com.esgi.modules.user.domain.UserId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public ChallengeController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
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

    @GetMapping
    public ResponseEntity<RunChallengeResponse> runChallenge(@RequestHeader("authorization") String token) {
        UserId userId = (UserId) commandBus.send(new DecodeTokenCommand(new Token(token)));
        List<ChallengeOutputResponse> challengeOutput =
                ((List<ChallengeOutput>) queryBus.send(
                        new RunChallengeQuery(userId.getValue()))).stream().map(ChallengeOutputMapper::toDto).toList();
        return ResponseEntity.ok(new RunChallengeResponse(challengeOutput));
    }
}
