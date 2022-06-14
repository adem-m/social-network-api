package com.esgi.modules.file.application;

import com.esgi.kernel.Query;

public record RetrieveImageQuery(String fileName) implements Query {
}
