package com.esgi;

import com.esgi.kernel.*;
import com.esgi.modules.friendship.application.*;
import com.esgi.modules.friendship.domain.FriendshipRepository;
import com.esgi.modules.infrastructure.DefaultEventDispatcher;
import com.esgi.modules.infrastructure.InMemoryFriendshipRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class FriendshipConfiguration {
    @Bean
    public FriendshipRepository friendshipRepository() {
        return new InMemoryFriendshipRepository();
    }

    @Bean
    public EventDispatcher<Event> friendshipEventEventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(AddFriendshipEvent.class, List.of(new AddFriendshipEventListener()));
        return new DefaultEventDispatcher(listenerMap);
    }

    @Bean
    public AddFriendshipCommandHandler addFriendshipCommandHandler() {
        return new AddFriendshipCommandHandler(friendshipRepository(), friendshipEventEventDispatcher());
    }

    @Bean
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap =
                Collections.singletonMap(AddFriendship.class, new AddFriendshipCommandHandler(friendshipRepository(), friendshipEventEventDispatcher()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    @Bean
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap =
                Collections.singletonMap(RetrieveFriends.class, new RetrieveFriendsHandler(friendshipRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }

    @Bean
    public RetrieveFriendsHandler retrievePostsHandler() {
        return new RetrieveFriendsHandler(friendshipRepository());
    }
}
