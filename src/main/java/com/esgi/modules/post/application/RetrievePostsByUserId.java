package com.esgi.modules.post.application;

import com.esgi.kernel.Query;
import com.esgi.modules.user.domain.UserId;

public class RetrievePostsByUserId implements Query {
    UserId id;

    public RetrievePostsByUserId(UserId id){
        this.id = id;
    }
}
