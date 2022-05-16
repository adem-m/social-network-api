package com.esgi.modules.challenge.domain;

import com.esgi.kernel.Repository;
import com.esgi.modules.code.domain.CodeId;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public interface ChallengeEntryRepository extends Repository<ChallengeEntryId, ChallengeEntry> {
    List<CodeId> findCodeIdsByUserId(UserId userId);
}
