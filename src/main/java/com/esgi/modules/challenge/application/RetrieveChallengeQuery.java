package com.esgi.modules.challenge.application;

import com.esgi.kernel.Query;

public record RetrieveChallengeQuery(String userId) implements Query {
}
