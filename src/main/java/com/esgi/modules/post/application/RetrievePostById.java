package com.esgi.modules.post.application;

import com.esgi.kernel.Query;
import com.esgi.modules.post.domain.PostId;

public class RetrievePostById implements Query {
    PostId id;

    public RetrievePostById(PostId id){
        this.id = id;
    }
}
