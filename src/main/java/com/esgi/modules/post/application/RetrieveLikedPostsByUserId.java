package com.esgi.modules.post.application;

import com.esgi.kernel.Query;
import com.esgi.modules.user.domain.UserId;

public class RetrieveLikedPostsByUserId implements Query {
    UserId id;

    public RetrieveLikedPostsByUserId(UserId id){
        this.id = id;
    }
}
