package com.esgi.modules.follow.exposition;

import java.util.List;

@SuppressWarnings("all")
public class FollowsResponse {
    public final List<FollowResponse> follows;

    public FollowsResponse(List<FollowResponse> follows) {
        this.follows = follows;
    }
}
