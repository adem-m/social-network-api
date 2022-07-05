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
    public String oldPassword;
    public String newPassword;
    public String firstName;
    public String lastName;
    public MultipartFile image;

    public UpdateUser(String userId, Email email, String oldPassword, String newPassword, String firstName, String lastName, MultipartFile image) {
        this.userId = userId;
        this.email = email;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }
}
