package com.esgi.modules.friendship.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.friendship.domain.Friendship;
import com.esgi.modules.friendship.domain.FriendshipRepository;

import java.util.List;

public class RetrieveFriendsHandler implements QueryHandler<RetrieveFriends, List<Friendship>> {
    private final FriendshipRepository friendshipRepository;

    public RetrieveFriendsHandler(FriendshipRepository friendshipRepository){
        this.friendshipRepository = friendshipRepository;
    }

    @Override
    public List<Friendship> handle(RetrieveFriends query) {
        return friendshipRepository.findByUserId(query.id);
    }
}
