package com.esgi.modules.security.infrastructure;

import com.esgi.modules.security.domain.EncryptionService;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class GuavaEncryptionService implements EncryptionService {

    @Override
    public String encrypt(String password) {
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }
}
