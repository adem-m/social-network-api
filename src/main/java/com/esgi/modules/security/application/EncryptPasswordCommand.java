package com.esgi.modules.security.application;

import com.esgi.kernel.Command;

public record EncryptPasswordCommand(String password) implements Command {
}
