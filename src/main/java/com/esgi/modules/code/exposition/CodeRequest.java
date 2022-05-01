package com.esgi.modules.code.exposition;

import javax.validation.constraints.NotNull;

public class CodeRequest {
    @NotNull
    public String source;

    @NotNull
    public String language;
}
