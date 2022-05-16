package com.esgi.modules.code.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.code.domain.Code;
import com.esgi.modules.code.domain.CodeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record RetrieveCodeByIdHandler(CodeRepository codeRepository) implements QueryHandler<RetrieveCodeById, Code> {
    @Override
    public Code handle(RetrieveCodeById query) {
        log.info("Retrieving code with id {}", query.codeId().getValue());
        return codeRepository.findById(query.codeId());
    }
}
