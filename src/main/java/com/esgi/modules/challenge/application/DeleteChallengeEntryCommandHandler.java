package com.esgi.modules.challenge.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.ForbiddenOperationException;
import com.esgi.modules.challenge.domain.ChallengeEntry;
import com.esgi.modules.challenge.domain.ChallengeEntryId;
import com.esgi.modules.challenge.domain.ChallengeEntryRepository;
import com.esgi.modules.code.domain.CodeId;
import com.esgi.modules.user.domain.UserId;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record DeleteChallengeEntryCommandHandler(
        ChallengeEntryRepository challengeEntryRepository) implements CommandHandler<DeleteChallengeEntryCommand, Void> {
    @Override
    public Void handle(DeleteChallengeEntryCommand command) {
        ChallengeEntry challengeEntry =
                challengeEntryRepository.findByCodeIdAndUserId(new CodeId(command.codeId()), new UserId(command.userId()));
        if (!challengeEntry.userId().getValue().equals(command.userId()))
            throw new ForbiddenOperationException("You can't delete this challenge entry");
        challengeEntryRepository.delete(challengeEntry.id());
        log.info("Challenge entry {} deleted", challengeEntry.id().value());
        return null;
    }
}
