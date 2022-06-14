package com.esgi.modules.file.domain;

public interface FileRepository {
    void save(byte[] file, String fileName);

    Base64File get(String fileName);

    void delete(String fileName);
}
