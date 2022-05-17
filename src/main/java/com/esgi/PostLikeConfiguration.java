package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.post.application.*;
import com.esgi.modules.post.domain.PostLikeRepository;
import com.esgi.modules.post.infrastructure.SpringDataPostLikeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostLikeConfiguration {
    private final KernelConfiguration kernelConfiguration;

    public PostLikeConfiguration(KernelConfiguration kernelConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
    }

    @Bean
    public PostLikeRepository postLikeRepository() {
        return new SpringDataPostLikeRepository();
    }

    @Bean
    public LikePostEventListener likePostEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        LikePostEventListener listener = new LikePostEventListener();
        dispatcher.addListener(LikePostEvent.class, listener);
        return listener;
    }

    @Bean
    public LikePostCommandHandler likePostCommandHandler() {
        return new LikePostCommandHandler(postLikeRepository(), kernelConfiguration.eventDispatcher(), kernelConfiguration.commandBus());
    }

    @Bean
    public CommandBus likePostCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(LikePost.class, likePostCommandHandler());
        return commandBus;
    }

    @Bean
    public UnlikePostEventListener unlikePostEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        UnlikePostEventListener listener = new UnlikePostEventListener();
        dispatcher.addListener(UnlikePostEvent.class, listener);
        return listener;
    }

    @Bean
    public UnlikePostCommandHandler unlikePostCommandHandler() {
        return new UnlikePostCommandHandler(postLikeRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus unlikePostCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(UnlikePost.class, unlikePostCommandHandler());
        return commandBus;
    }

    @Bean
    public QueryBus likePostQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveLikedPostsByUserId.class, new RetrieveLikedPostsByUserIdHandler(postLikeRepository()));
        queryBus.addHandler(CountPostLikesQuery.class, new CountPostLikesQueryHandler(postLikeRepository()));
        return queryBus;
    }

    @Bean
    public RetrieveLikedPostsByUserIdHandler retrieveLikedPostsByUserIdHandler() {
        return new RetrieveLikedPostsByUserIdHandler(postLikeRepository());
    }
}
