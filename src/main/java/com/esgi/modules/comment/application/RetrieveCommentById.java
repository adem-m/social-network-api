package com.esgi.modules.comment.application;

import com.esgi.kernel.Query;

public class RetrieveCommentById implements Query {
    String id;

    public RetrieveCommentById(String id){
        this.id = id;
    }
}
