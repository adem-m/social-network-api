package com.esgi.modules.authentication.application;

import com.esgi.kernel.Command;
import com.esgi.modules.authentication.domain.Email;
import com.esgi.modules.authentication.domain.Password;

public record LoginCommand(Email email, Password password) implements Command {
}