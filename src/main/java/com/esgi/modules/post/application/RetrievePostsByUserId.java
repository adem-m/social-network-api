package com.esgi.modules.post.application;

import com.esgi.kernel.Query;
import com.esgi.modules.user.domain.UserId;

public class RetrievePostsByUserId implements Query {
    int id;

    public RetrievePostsByUserId(int id){
        this.id = id;
    }
}
