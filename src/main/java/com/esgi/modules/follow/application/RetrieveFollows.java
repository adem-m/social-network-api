package com.esgi.modules.follow.application;

import com.esgi.kernel.Query;
import com.esgi.modules.user.domain.UserId;

public class RetrieveFollows implements Query {
    UserId id;

    public RetrieveFollows(UserId id){
        this.id = id;
    }
}