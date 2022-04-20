package com.esgi.modules.follow.application;

import com.esgi.kernel.Query;

public class RetrieveFollowers implements Query {
    String id;

    public RetrieveFollowers(String id) {
        this.id = id;
    }
}
