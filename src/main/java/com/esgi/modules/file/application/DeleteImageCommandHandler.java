package com.esgi.modules.file.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.modules.file.domain.FileRepository;
import org.springframework.beans.factory.annotation.Value;

public class DeleteImageCommandHandler implements CommandHandler<DeleteImageCommand, Void> {
    private final FileRepository fileRepository;
    @Value("${image.thumbnail.suffix}")
    private String thumbnailSuffix;

    public DeleteImageCommandHandler(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public Void handle(DeleteImageCommand command) {
        fileRepository.delete(command.fileName());
        fileRepository.delete(command.fileName() + thumbnailSuffix);
        return null;
    }
}
