package com.esgi.modules.file.application;

import com.esgi.kernel.Command;

public record DeleteImageCommand(String fileName) implements Command {
}
