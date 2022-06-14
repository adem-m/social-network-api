package com.esgi.modules.file.domain;

public interface ImageResizeService {
    byte[] resize(byte[] file, int width, int height);
}
