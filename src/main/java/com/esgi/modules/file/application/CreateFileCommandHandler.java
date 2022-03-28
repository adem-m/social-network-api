package com.esgi.modules.file.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

final public class CreateFileCommandHandler implements CommandHandler<CreateFile, Void> {
    private final EventDispatcher<Event> eventEventDispatcher;

    public CreateFileCommandHandler(EventDispatcher<Event> eventEventDispatcher) {
        this.eventEventDispatcher = eventEventDispatcher;
    }

    @Override
    public Void handle(CreateFile command) {
        try {
            File targetFile = new File(command.filePath());
            if (targetFile.exists()) {
                targetFile.delete();
                targetFile.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(targetFile);
            outputStream.write(command.content().getBytes(StandardCharsets.UTF_8));
            outputStream.close();
            eventEventDispatcher.dispatch(new CreateFileEvent(command.filePath()));
        } catch (IOException e) {
            throw new FileCreationException(command.filePath());
        }
        return null;
    }
}
