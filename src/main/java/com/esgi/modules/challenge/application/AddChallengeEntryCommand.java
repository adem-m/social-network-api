package com.esgi.modules.challenge.application;

import com.esgi.kernel.Command;

public record AddChallengeEntryCommand(String userId, String codeId) implements Command {
}
