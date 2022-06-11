package com.esgi.modules.challenge.exposition;

import java.util.List;

public record GetChallengeResponse(List<ChallengeEntry> codes) {
}

record ChallengeEntry(String id, String source, String language) {
}