package com.esgi.modules.user.exposition;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateUserRequest {
    @NotBlank
    @NotNull
    public String email;
    @NotBlank
    @NotNull
    public String password;
}
