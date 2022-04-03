package com.esgi.modules.comment.application;

import com.esgi.kernel.Query;
import com.esgi.modules.user.domain.UserId;

public class RetrieveLikedCommentsByUserId implements Query {
    UserId id;

    public RetrieveLikedCommentsByUserId(UserId id){
        this.id = id;
    }
}
