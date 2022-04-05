package com.esgi.modules.post.application;

import com.esgi.kernel.Query;

public class RetrievePostsByUserId implements Query {
    int id;

    public RetrievePostsByUserId(int id){
        this.id = id;
    }
}
