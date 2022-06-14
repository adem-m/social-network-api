package com.esgi.modules.file.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.file.domain.FileRepository;

public class RetrieveImageQueryHandler implements QueryHandler<RetrieveImageQuery, String> {
    private final FileRepository fileRepository;

    public RetrieveImageQueryHandler(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public String handle(RetrieveImageQuery query) {
        return fileRepository.get(query.fileName()).value();
    }
}
