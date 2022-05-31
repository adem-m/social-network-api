package com.esgi.modules.security.application;

import com.esgi.kernel.CommandHandler;
import com.esgi.modules.security.domain.EncryptionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public record EncryptPasswordCommandHandler(
        EncryptionService encryptionService) implements CommandHandler<EncryptPasswordCommand, String> {
    @Override
    public String handle(EncryptPasswordCommand command) {
        log.info("Encrypting password");
        return encryptionService.encrypt(command.password());
    }
}
