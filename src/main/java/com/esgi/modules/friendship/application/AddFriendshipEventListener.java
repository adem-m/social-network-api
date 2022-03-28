package com.esgi.modules.friendship.application;

import com.esgi.kernel.EventListener;

public class AddFriendshipEventListener implements EventListener<AddFriendshipEvent> {
    @Override
    public void listenTo(AddFriendshipEvent event) {
        System.out.println("listening AddFriendshipEvent.");
    }
}
