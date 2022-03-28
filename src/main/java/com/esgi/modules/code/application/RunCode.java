package com.esgi.modules.code.application;

import com.esgi.kernel.Command;
import com.esgi.modules.code.domain.Code;

public record RunCode(Code code) implements Command {
}
