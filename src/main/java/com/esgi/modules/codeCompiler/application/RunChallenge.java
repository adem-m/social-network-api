package com.esgi.modules.codeCompiler.application;

import com.esgi.kernel.Command;
import com.esgi.modules.code.domain.CodeId;

import java.util.List;

public record RunChallenge(List<CodeId> codeIds) implements Command {
}
