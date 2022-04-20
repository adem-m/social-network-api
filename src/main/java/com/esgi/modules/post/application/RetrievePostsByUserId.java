package com.esgi.modules.post.application;

import com.esgi.kernel.Query;

public class RetrievePostsByUserId implements Query {
    String id;

    public RetrievePostsByUserId(String id){
        this.id = id;
    }
}
