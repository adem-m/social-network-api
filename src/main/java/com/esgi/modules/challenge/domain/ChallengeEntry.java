package com.esgi.modules.challenge.domain;

import com.esgi.modules.code.domain.CodeId;
import com.esgi.modules.user.domain.UserId;

public record ChallengeEntry(ChallengeEntryId id, UserId userId, CodeId codeId) {
}
