package com.esgi.modules.comment.application;

import com.esgi.kernel.Query;
import com.esgi.modules.user.domain.UserId;

public class RetrieveComments implements Query {
    UserId id;

    public RetrieveComments(UserId id){
        this.id = id;
    }
}
