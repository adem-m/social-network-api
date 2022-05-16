package com.esgi.modules.challenge.exposition;

import java.util.List;

public record RunChallengeResponse(List<ChallengeOutputResponse> outputs) {
}

record ChallengeOutputResponse(CodeResponse code, OutputResponse output) {
}

record CodeResponse(String source, String language) {
}

record OutputResponse(String value, String status, long duration) {
}