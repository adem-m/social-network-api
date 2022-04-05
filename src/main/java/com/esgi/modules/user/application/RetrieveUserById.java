package com.esgi.modules.user.application;

import com.esgi.kernel.Query;

public class RetrieveUserById implements Query {
    public final int id;

    public RetrieveUserById(int id){
        this.id = id;
    }
}
