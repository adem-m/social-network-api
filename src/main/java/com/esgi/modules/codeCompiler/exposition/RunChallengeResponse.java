package com.esgi.modules.codeCompiler.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

final public class RunChallengeResponse {
    @NotNull
    @NotBlank
    public List<ChallengeOutput> outputs;

    public RunChallengeResponse(List<ChallengeOutput> outputs) {
        this.outputs = outputs;
    }
}

class ChallengeOutput {
    public RunCodeResponse output;
    public Code code;

    public ChallengeOutput(RunCodeResponse output, Code code) {
        this.output = output;
        this.code = code;
    }
}

class Code {
    public String code;
    public String language;

    public Code(String code, String language) {
        this.code = code;
        this.language = language;
    }
}