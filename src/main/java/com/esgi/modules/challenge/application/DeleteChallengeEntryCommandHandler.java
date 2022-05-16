package com.esgi.modules.challenge.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.ForbiddenOperationException;
import com.esgi.modules.challenge.domain.ChallengeEntry;
import com.esgi.modules.challenge.domain.ChallengeEntryId;
import com.esgi.modules.challenge.domain.ChallengeEntryRepository;

public record DeleteChallengeEntryCommandHandler(
        ChallengeEntryRepository challengeEntryRepository) implements CommandHandler<DeleteChallengeEntryCommand, Void> {
    @Override
    public Void handle(DeleteChallengeEntryCommand command) {
        ChallengeEntry challengeEntry =
                challengeEntryRepository.findById(new ChallengeEntryId(command.challengeEntryId()));
        if (!challengeEntry.userId().getValue().equals(command.userId()))
            throw new ForbiddenOperationException("You can't delete this challenge entry");
        challengeEntryRepository.delete(new ChallengeEntryId(command.challengeEntryId()));
        return null;
    }
}
