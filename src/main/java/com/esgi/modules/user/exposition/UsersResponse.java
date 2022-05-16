package com.esgi.modules.user.exposition;

import java.util.List;

@SuppressWarnings("all")
public class UsersResponse {
    public final List<UserResponse> members;

    public UsersResponse(List<UserResponse> members) {
        this.members = members;
    }
}
