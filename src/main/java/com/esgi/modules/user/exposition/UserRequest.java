package com.esgi.modules.user.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserRequest {
    @NotNull
    @NotBlank
    public String lastname;

    @NotNull
    @NotBlank
    public String firstname;

    @NotNull
    public EmailRequest email;

    public String password;
}
