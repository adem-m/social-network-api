package com.esgi.modules.file.application;

import com.esgi.kernel.ApplicationEvent;

public record CreateFileEvent(String filePath) implements ApplicationEvent {
}
