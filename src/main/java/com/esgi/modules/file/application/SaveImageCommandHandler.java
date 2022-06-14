package com.esgi.modules.file.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.modules.file.domain.FileRepository;

public class SaveImageCommandHandler implements CommandHandler<SaveImageCommand, Void> {
    private final FileRepository fileRepository;

    public SaveImageCommandHandler(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Void handle(SaveImageCommand command) {
        fileRepository.save(command.file(), command.fileName());
        return null;
    }
}
