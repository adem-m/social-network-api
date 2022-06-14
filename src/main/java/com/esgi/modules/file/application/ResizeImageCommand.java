package com.esgi.modules.file.application;

import com.esgi.kernel.Command;

public record ResizeImageCommand(byte[] file, int width, int height) implements Command {
}
