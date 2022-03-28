package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.friendship.application.*;
import com.esgi.modules.friendship.domain.FriendshipRepository;
import com.esgi.modules.infrastructure.InMemoryFriendshipRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FriendshipConfiguration {
    private final KernelConfiguration kernelConfiguration;

    public FriendshipConfiguration(KernelConfiguration kernelConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
    }

    @Bean
    public FriendshipRepository friendshipRepository() {
        return new InMemoryFriendshipRepository();
    }

    @Bean
    public AddFriendshipEventListener addFriendshipEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        AddFriendshipEventListener listener = new AddFriendshipEventListener();
        dispatcher.addListener(AddFriendshipEvent.class, listener);
        return listener;
    }

    @Bean
    public AddFriendshipCommandHandler addFriendshipCommandHandler() {
        return new AddFriendshipCommandHandler(friendshipRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus friendshipCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(AddFriendship.class, addFriendshipCommandHandler());
        return commandBus;
    }

    @Bean
    public QueryBus friendshipQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveFriends.class, new RetrieveFriendsHandler(friendshipRepository()));
        return queryBus;
    }

    @Bean
    public RetrieveFriendsHandler retrieveFriendsHandler() {
        return new RetrieveFriendsHandler(friendshipRepository());
    }
}
