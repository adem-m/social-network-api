package com.esgi.modules.post.application;

import com.esgi.kernel.Query;
import com.esgi.modules.user.domain.UserId;

public class RetrievePosts implements Query {
    UserId id;

    public RetrievePosts(UserId id){
        this.id = id;
    }
}
