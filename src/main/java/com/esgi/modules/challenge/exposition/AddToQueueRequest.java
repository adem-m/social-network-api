package com.esgi.modules.challenge.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

final public class AddToQueueRequest {
    @NotNull
    @NotBlank
    public String codeId;
}
