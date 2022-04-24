package com.esgi.modules.post.application;

import com.esgi.kernel.Command;
import com.esgi.kernel.CommandBus;
import com.esgi.modules.code.exposition.CodeRequest;

import java.util.Objects;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class CreatePost implements Command {
    public String content;
    public CodeRequest code;
    public final String creatorId;
    public final CommandBus commandBus;

    public CreatePost(String content, CodeRequest code, String creatorId, CommandBus commandBus) {
        this.content = content;
        this.code = code;
        this.creatorId = Objects.requireNonNull(creatorId);
        this.commandBus = commandBus;
    }
}