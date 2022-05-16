package com.esgi.modules.code.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.code.domain.Code;
import com.esgi.modules.code.domain.CodeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetrieveCodeByPostIdHandler implements QueryHandler<RetrieveCodeByPostId, Code> {
    private final CodeRepository codeRepository;

    public RetrieveCodeByPostIdHandler(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public Code handle(RetrieveCodeByPostId query) {
        log.info("Code retrieved by post id {}", query.id);
        return codeRepository.findByPostId(query.id);
    }
}
