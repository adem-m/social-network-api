package com.esgi.modules.friendship.application;

import com.esgi.kernel.Query;
import com.esgi.modules.user.domain.UserId;

public class RetrieveFriends implements Query {
    UserId id;

    public RetrieveFriends(UserId id){
        this.id = id;
    }
}
