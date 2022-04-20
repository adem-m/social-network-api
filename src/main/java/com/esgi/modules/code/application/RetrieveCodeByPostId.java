package com.esgi.modules.code.application;

import com.esgi.kernel.Query;

public class RetrieveCodeByPostId implements Query {
    int id;

    public RetrieveCodeByPostId(int id){
        this.id = id;
    }
}
