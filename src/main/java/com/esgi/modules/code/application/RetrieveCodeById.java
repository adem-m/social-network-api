package com.esgi.modules.code.application;

import com.esgi.kernel.Query;
import com.esgi.modules.code.domain.CodeId;

public record RetrieveCodeById(CodeId codeId) implements Query {
}
