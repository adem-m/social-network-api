package com.esgi.modules.challenge.infastructure;

import com.esgi.modules.challenge.domain.ChallengeEntry;
import com.esgi.modules.challenge.domain.ChallengeEntryId;
import com.esgi.modules.code.domain.CodeId;
import com.esgi.modules.user.domain.UserId;

public final class ChallengeEntryMapper {
    public static ChallengeEntry toDomain(ChallengeEntryEntity entity) {
        return new ChallengeEntry(
                new ChallengeEntryId(entity.getId()),
                new UserId(entity.getUserId()),
                new CodeId(entity.getCodeId())
        );
    }

    public static ChallengeEntryEntity toEntity(ChallengeEntry challengeEntry) {
        return new ChallengeEntryEntity(
                challengeEntry.id().value(),
                challengeEntry.userId().getValue(),
                challengeEntry.codeId().getValue()
        );
    }
}
