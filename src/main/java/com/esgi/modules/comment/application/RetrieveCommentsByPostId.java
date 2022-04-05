package com.esgi.modules.comment.application;

import com.esgi.kernel.Query;

public class RetrieveCommentsByPostId implements Query {
    int id;

    public RetrieveCommentsByPostId(int id){
        this.id = id;
    }
}
