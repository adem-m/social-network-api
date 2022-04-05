package com.esgi.modules.follow.application;

import com.esgi.kernel.Query;

public class RetrieveFollowing implements Query {
    int id;

    public RetrieveFollowing(int id){
        this.id = id;
    }
}