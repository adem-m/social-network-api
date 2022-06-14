package com.esgi.modules.user.application;

import com.esgi.kernel.Query;

public class RetrieveUserById implements Query {
    public final String id;
    public final String userId;
    public boolean thumbnail = true;

    public RetrieveUserById(String id) {
        this.id = id;
        this.userId = null;
    }

    public RetrieveUserById(String id, String userId, boolean thumbnail) {
        this.id = id;
        this.userId = userId;
        this.thumbnail = thumbnail;
    }
}
