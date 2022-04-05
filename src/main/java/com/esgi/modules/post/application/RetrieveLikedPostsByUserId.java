package com.esgi.modules.post.application;

import com.esgi.kernel.Query;

public class RetrieveLikedPostsByUserId implements Query {
    int id;

    public RetrieveLikedPostsByUserId(int id){
        this.id = id;
    }
}
