package com.esgi.modules.comment.application;

import com.esgi.kernel.Query;

public class RetrieveLikedCommentsByUserId implements Query {
    int id;

    public RetrieveLikedCommentsByUserId(int id){
        this.id = id;
    }
}
