package com.esgi.modules.post.application;

import com.esgi.kernel.Query;

public class RetrievePostById implements Query {
    String id;

    public RetrievePostById(String id){
        this.id = id;
    }
}
