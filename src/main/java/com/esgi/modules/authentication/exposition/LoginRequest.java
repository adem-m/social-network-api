package com.esgi.modules.authentication.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

final public class LoginRequest {
    @NotNull
    @NotBlank
    public String email;

    @NotNull
    @NotBlank
    public String password;
}
