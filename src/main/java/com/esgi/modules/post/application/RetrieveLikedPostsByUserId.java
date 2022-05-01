package com.esgi.modules.post.application;

import com.esgi.kernel.Query;

public class RetrieveLikedPostsByUserId implements Query {
    String id;

    public RetrieveLikedPostsByUserId(String id){
        this.id = id;
    }
}
