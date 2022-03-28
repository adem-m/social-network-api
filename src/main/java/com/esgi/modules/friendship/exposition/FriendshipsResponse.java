package com.esgi.modules.friendship.exposition;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@SuppressWarnings("all")
@XmlRootElement
public class FriendshipsResponse {
    public final List<FriendshipResponse> friendships;

    public FriendshipsResponse(List<FriendshipResponse> friendships) {
        this.friendships = friendships;
    }
}
