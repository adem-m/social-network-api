package com.esgi.modules.file.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.modules.file.domain.ImageResizeService;

public class ResizeImageCommandHandler implements CommandHandler<ResizeImageCommand, byte[]> {
    private final ImageResizeService imageResizeService;

    public ResizeImageCommandHandler(ImageResizeService imageResizeService) {
        this.imageResizeService = imageResizeService;
    }

    @Override
    public byte[] handle(ResizeImageCommand command) {
        return imageResizeService.resize(command.file(), command.width(), command.height());
    }
}
