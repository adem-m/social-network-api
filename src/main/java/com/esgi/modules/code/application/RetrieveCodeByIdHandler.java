package com.esgi.modules.code.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.code.domain.Code;
import com.esgi.modules.code.domain.CodeRepository;

public record RetrieveCodeByIdHandler(CodeRepository codeRepository) implements QueryHandler<RetrieveCodeById, Code> {
    @Override
    public Code handle(RetrieveCodeById query) {
        return codeRepository.findById(query.codeId());
    }
}
