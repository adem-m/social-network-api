package com.esgi.modules.codeCompiler.exposition;

import javax.validation.constraints.NotNull;
import java.util.List;

final public class RunChallengeRequest {
    @NotNull
    public List<String> codeIds;
}
