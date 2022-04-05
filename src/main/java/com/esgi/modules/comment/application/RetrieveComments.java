package com.esgi.modules.comment.application;

import com.esgi.kernel.Query;

public class RetrieveComments implements Query {
    int id;

    public RetrieveComments(int id){
        this.id = id;
    }
}
