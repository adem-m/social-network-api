package com.esgi.modules.user.application;

import com.esgi.kernel.Query;

public class RetrieveUserById implements Query {
    public final String id;

    public RetrieveUserById(String id){
        this.id = id;
    }
}
