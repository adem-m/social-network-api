package com.esgi.modules.follow.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.follow.domain.FollowId;

public class UnfollowEvent implements ApplicationEvent {
    private final FollowId followId;

    public UnfollowEvent(FollowId followId){
        this.followId = followId;
    }
}
