package com.esgi.modules.challenge.domain;

import com.esgi.modules.codeCompiler.domain.Code;
import com.esgi.modules.codeCompiler.domain.Output;

public record ChallengeOutput(Code code, Output output, boolean expectedOutputCompliant) {
}
