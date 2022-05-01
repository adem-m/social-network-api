package com.esgi.modules.user.application;

import com.esgi.kernel.Query;

public class RetrieveUserByEmail implements Query {
    public String email;

    public RetrieveUserByEmail(String email){
        this.email = email;
    }
}
