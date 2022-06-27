package com.esgi.modules.user.application;

import com.esgi.kernel.Command;
import com.esgi.modules.user.domain.Email;
import org.springframework.web.multipart.MultipartFile;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class UpdateUser implements Command {
    public final String userId;
    public Email email;
    public String password;
    public MultipartFile image;

    public UpdateUser(String userId, Email email, String password, MultipartFile image) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.image = image;
    }
}
