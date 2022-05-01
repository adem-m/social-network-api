package com.esgi.modules.post.application;

import com.esgi.kernel.Query;

public class RetrieveFeedByUserId implements Query {
    String id;

    public RetrieveFeedByUserId(String id){
        this.id = id;
    }
}
