package com.esgi.modules.user.application;

import com.esgi.kernel.Query;
import com.esgi.modules.user.domain.UserId;

public class RetrieveUserById implements Query {
    UserId id;

    public RetrieveUserById(UserId id){
        this.id = id;
    }
}
