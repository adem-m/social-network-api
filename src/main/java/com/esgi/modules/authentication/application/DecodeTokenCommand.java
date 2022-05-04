package com.esgi.modules.authentication.application;

import com.esgi.kernel.Command;
import com.esgi.modules.authentication.domain.Token;

public record DecodeTokenCommand(Token token) implements Command {
}
