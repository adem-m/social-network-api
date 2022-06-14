package com.esgi.modules.file.application;

import com.esgi.kernel.Command;

public record SaveImageCommand(byte[] file, String fileName) implements Command {
}
