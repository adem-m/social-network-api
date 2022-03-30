package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.follow.application.*;
import com.esgi.modules.follow.domain.FollowRepository;
import com.esgi.modules.infrastructure.InMemoryFollowRepository;
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
    public QueryBus followQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveFollows.class, new RetrieveFollowsHandler(followRepository()));
        return queryBus;
    }

    @Bean
    public RetrieveFollowsHandler retrieveFollowsHandler() {
        return new RetrieveFollowsHandler(followRepository());
    }
}
