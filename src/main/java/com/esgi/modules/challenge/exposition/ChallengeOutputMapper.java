package com.esgi.modules.challenge.exposition;

import com.esgi.modules.challenge.domain.ChallengeOutput;

public class ChallengeOutputMapper {

    public static ChallengeOutputResponse toDto(ChallengeOutput challengeOutput) {
        return new ChallengeOutputResponse(
                new CodeResponse(
                        challengeOutput.code().source(),
                        challengeOutput.code().language().name().toLowerCase()),
                new OutputResponse(
                        challengeOutput.output().getValue(),
                        challengeOutput.output().getStatus().name().toLowerCase(),
                        challengeOutput.output().getDuration().value())
        );
    }
}
