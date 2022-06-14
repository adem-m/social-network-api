package com.esgi.modules.user.application;

import com.esgi.kernel.Command;
import com.esgi.modules.user.domain.Email;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * Command object
 */
@SuppressWarnings("all")
public final class CreateUser implements Command {
    public final String lastname;
    public final String firstname;
    public Email email;
    public String password;
    public MultipartFile image;

    public CreateUser(String lastname, String firstname, Email email, String password, MultipartFile image) {
        this.lastname = Objects.requireNonNull(lastname);
        this.firstname = Objects.requireNonNull(firstname);
        this.email = Objects.requireNonNull(email);
        this.password = Objects.requireNonNull(password);
        this.image = image;
    }
}