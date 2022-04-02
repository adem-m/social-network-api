package com.esgi.modules.follow.application;

import com.esgi.kernel.Query;
import com.esgi.modules.user.domain.UserId;

public class RetrieveFollowers implements Query {
    UserId id;

    public RetrieveFollowers(UserId id) {
        this.id = id;
    }
}
