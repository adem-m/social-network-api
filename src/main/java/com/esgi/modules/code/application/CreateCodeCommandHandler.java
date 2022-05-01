package com.esgi.modules.code.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.modules.code.domain.Code;
import com.esgi.modules.code.domain.CodeId;
import com.esgi.modules.code.domain.CodeRepository;

public final class CreateCodeCommandHandler implements CommandHandler<CreateCode, CodeId> {
    private final CodeRepository codeRepository;
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreateCodeCommandHandler(CodeRepository codeRepository, EventDispatcher<Event> eventEventDispatcher) {
        this.codeRepository = codeRepository;
        this.eventEventDispatcher = eventEventDispatcher;
    }

    public CodeId handle(CreateCode createCode) {
        final CodeId codeId = codeRepository.nextIdentity();
        Code code = new Code(codeId, createCode.postId, createCode.source, createCode.language);
        codeRepository.add(code);
        eventEventDispatcher.dispatch(new CreateCodeEvent(codeId));
        return codeId;
    }
}
