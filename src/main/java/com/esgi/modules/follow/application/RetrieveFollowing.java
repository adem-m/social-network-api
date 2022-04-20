package com.esgi.modules.follow.application;

import com.esgi.kernel.Query;

public class RetrieveFollowing implements Query {
    String id;

    public RetrieveFollowing(String id){
        this.id = id;
    }
}