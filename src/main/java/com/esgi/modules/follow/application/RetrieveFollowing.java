package com.esgi.modules.follow.application;

import com.esgi.kernel.Query;
import com.esgi.modules.user.domain.UserId;

public class RetrieveFollowing implements Query {
    UserId id;

    public RetrieveFollowing(UserId id){
        this.id = id;
    }
}