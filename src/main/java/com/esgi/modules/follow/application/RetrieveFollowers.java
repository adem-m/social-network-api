package com.esgi.modules.follow.application;

import com.esgi.kernel.Query;

public class RetrieveFollowers implements Query {
    int id;

    public RetrieveFollowers(int id) {
        this.id = id;
    }
}
