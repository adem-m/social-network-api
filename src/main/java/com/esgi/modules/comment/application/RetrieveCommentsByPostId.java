package com.esgi.modules.comment.application;

import com.esgi.kernel.Query;

public class RetrieveCommentsByPostId implements Query {
    String id;

    public RetrieveCommentsByPostId(String id){
        this.id = id;
    }
}
