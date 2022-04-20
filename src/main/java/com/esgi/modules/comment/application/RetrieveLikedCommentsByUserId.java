package com.esgi.modules.comment.application;

import com.esgi.kernel.Query;

public class RetrieveLikedCommentsByUserId implements Query {
    String id;

    public RetrieveLikedCommentsByUserId(String id){
        this.id = id;
    }
}
