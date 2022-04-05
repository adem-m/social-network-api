package com.esgi.modules.comment.application;

import com.esgi.kernel.Query;
import com.esgi.modules.comment.domain.CommentId;

public class RetrieveCommentById implements Query {
    int id;

    public RetrieveCommentById(int id){
        this.id = id;
    }
}
