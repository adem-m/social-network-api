package com.esgi.modules.comment.application;

import com.esgi.kernel.Query;

public class RetrieveComments implements Query {
    String id;

    public RetrieveComments(String id){
        this.id = id;
    }
}
