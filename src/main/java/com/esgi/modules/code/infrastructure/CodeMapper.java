package com.esgi.modules.code.infrastructure;

import com.esgi.modules.code.domain.Code;
import com.esgi.modules.code.domain.CodeId;
import com.esgi.modules.post.domain.PostId;
import org.springframework.stereotype.Component;

@Component
public class CodeMapper {
    public CodeEntity toEntity(Code code) {
        return new CodeEntity(
                code.getCodeId().getValue(),
                code.getPostId().getValue(),
                code.getSource(),
                code.getLanguage());
    }

    public Code toModel(CodeEntity codeEntity) {
        return new Code(
                new CodeId(codeEntity.getId()),
                new PostId(codeEntity.getPostId()),
                codeEntity.getSource(),
                codeEntity.getLanguage());
    }
}
