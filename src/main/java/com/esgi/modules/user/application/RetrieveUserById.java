package com.esgi.modules.user.application;

import com.esgi.kernel.Query;
import com.esgi.modules.user.domain.UserId;

public class RetrieveUserById implements Query {
    public final int id;

    public RetrieveUserById(int id){
        this.id = id;
    }
}
