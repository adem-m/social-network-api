package com.esgi.modules.user.application;

import com.esgi.kernel.Query;

public class RetrieveUsersByName implements Query {
    public final String name;
    public boolean thumbnail = true;

    public RetrieveUsersByName(String name){
        this.name = name;
    }

    public RetrieveUsersByName(String name, boolean thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }
}
