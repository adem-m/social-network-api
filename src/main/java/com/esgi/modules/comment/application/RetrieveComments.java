package com.esgi.modules.comment.application;

import com.esgi.kernel.Query;
import com.esgi.modules.user.domain.UserId;

public class RetrieveComments implements Query {
    int id;

    public RetrieveComments(int id){
        this.id = id;
    }
}
