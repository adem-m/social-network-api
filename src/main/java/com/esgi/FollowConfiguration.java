package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.follow.application.*;
import com.esgi.modules.follow.domain.FollowRepository;
import com.esgi.modules.follow.infrastructure.InMemoryFollowRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FollowConfiguration {
    private final KernelConfiguration kernelConfiguration;

    public FollowConfiguration(KernelConfiguration kernelConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
    }

    @Bean
    public FollowRepository followRepository() {
        return new InMemoryFollowRepository();
    }

    @Bean
    public CreateFollowEventListener createFollowEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        CreateFollowEventListener listener = new CreateFollowEventListener();
        dispatcher.addListener(CreateFollowEvent.class, listener);
        return listener;
    }

    @Bean
    public CreateFollowCommandHandler createFollowCommandHandler() {
        return new CreateFollowCommandHandler(followRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus followCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(CreateFollow.class, createFollowCommandHandler());
        return commandBus;
    }

    @Bean
    public UnfollowEventListener unfollowEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        UnfollowEventListener listener = new UnfollowEventListener();
        dispatcher.addListener(UnfollowEvent.class, listener);
        return listener;
    }

    @Bean
    public UnfollowCommandHandler unfollowCommandHandler() {
        return new UnfollowCommandHandler(followRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus unfollowCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(Unfollow.class, unfollowCommandHandler());
        return commandBus;
    }

    @Bean
    public QueryBus followingQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveFollowing.class, new RetrieveFollowingHandler(followRepository()));
        return queryBus;
    }

    @Bean
    public RetrieveFollowingHandler retrieveFollowingHandler() {
        return new RetrieveFollowingHandler(followRepository());
    }

    @Bean
    public QueryBus followersQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveFollowers.class, new RetrieveFollowersHandler(followRepository()));
        return queryBus;
    }

    @Bean
    public RetrieveFollowersHandler retrieveFollowersHandler() {
        return new RetrieveFollowersHandler(followRepository());
    }

    @Bean
    public QueryBus followsQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveFollows.class, new RetrieveFollowsHandler(followRepository()));
        return queryBus;
    }

    @Bean
    public RetrieveFollowsHandler retrieveFollowsHandler() {
        return new RetrieveFollowsHandler(followRepository());
    }
}
