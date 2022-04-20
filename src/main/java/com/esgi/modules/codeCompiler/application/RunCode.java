package com.esgi.modules.codeCompiler.application;

import com.esgi.kernel.Command;
import com.esgi.modules.codeCompiler.domain.Code;

public record RunCode(Code code) implements Command {
}
