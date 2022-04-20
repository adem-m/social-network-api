package com.esgi.modules.code.application;

import com.esgi.kernel.Query;

public class RetrieveCodeByPostId implements Query {
    String id;

    public RetrieveCodeByPostId(String id){
        this.id = id;
    }
}
