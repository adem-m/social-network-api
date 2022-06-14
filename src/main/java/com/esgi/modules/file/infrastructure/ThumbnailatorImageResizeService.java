package com.esgi.modules.file.infrastructure;

import com.esgi.modules.file.domain.ImageResizeService;
import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ThumbnailatorImageResizeService implements ImageResizeService {
    private static final double DEFAULT_QUALITY = 0.7;
    private static final String DEFAULT_FORMAT = "JPEG";

    @Override
    public byte[] resize(byte[] file, int width, int height) {
        InputStream inputStream = new ByteArrayInputStream(file);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Thumbnails.of(inputStream)
                    .size(width, height)
                    .outputFormat(DEFAULT_FORMAT)
                    .outputQuality(DEFAULT_QUALITY)
                    .toOutputStream(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
