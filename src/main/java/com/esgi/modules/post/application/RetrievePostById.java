package com.esgi.modules.post.application;

import com.esgi.kernel.Query;

public class RetrievePostById implements Query {
    int id;

    public RetrievePostById(int id){
        this.id = id;
    }
}
