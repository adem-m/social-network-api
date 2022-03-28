package com.esgi.modules.file.application;

import com.esgi.kernel.Command;

public record CreateFile(String filePath, String content) implements Command {
}
