package com.esgi.modules.challenge.application;

import com.esgi.kernel.AlreadyExistsException;
import com.esgi.kernel.CommandBus;
import com.esgi.kernel.CommandHandler;
import com.esgi.modules.challenge.domain.ChallengeEntry;
import com.esgi.modules.challenge.domain.ChallengeEntryId;
import com.esgi.modules.challenge.domain.ChallengeEntryRepository;
import com.esgi.modules.code.domain.CodeId;
import com.esgi.modules.user.domain.UserId;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record AddChallengeEntryCommandHandler(
        CommandBus commandBus,
        ChallengeEntryRepository challengeEntryRepository) implements CommandHandler<AddChallengeEntryCommand, ChallengeEntryId> {
    @Override
    public ChallengeEntryId handle(AddChallengeEntryCommand command) {
        ChallengeEntry challengeEntry =
                challengeEntryRepository.findByCodeIdAndUserId(new CodeId(command.codeId()), new UserId(command.userId()));
        if (challengeEntry != null) {
            log.error("Challenge entry {} already exists", challengeEntry.id().value());
            throw new AlreadyExistsException("Challenge entry already exists");
        }
        ChallengeEntryId challengeEntryId = challengeEntryRepository.nextIdentity();
        challengeEntryRepository.add(new ChallengeEntry(
                challengeEntryId,
                new UserId(command.userId()),
                new CodeId(command.codeId())));
        log.info("Added challenge entry {}", challengeEntryId.value());
        return challengeEntryId;
    }
}
