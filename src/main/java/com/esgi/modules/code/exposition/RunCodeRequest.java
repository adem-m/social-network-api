package com.esgi.modules.code.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public final class RunCodeRequest {
    @NotNull
    @NotBlank
    public String code;

    @NotNull
    @NotBlank
    public String language;
}
