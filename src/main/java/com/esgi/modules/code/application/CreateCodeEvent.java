package com.esgi.modules.code.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.code.domain.CodeId;

public class CreateCodeEvent  implements ApplicationEvent {
    private final CodeId codeId;

    public CreateCodeEvent(CodeId codeId) {
        this.codeId = codeId;
    }
}
