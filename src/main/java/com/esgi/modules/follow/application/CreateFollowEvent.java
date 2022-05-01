package com.esgi.modules.follow.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.follow.domain.FollowId;

public class CreateFollowEvent implements ApplicationEvent {
    private final FollowId followId;

    public CreateFollowEvent(FollowId followId){
        this.followId = followId;
    }
}
