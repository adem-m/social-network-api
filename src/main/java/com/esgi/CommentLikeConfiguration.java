package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.comment.application.*;
import com.esgi.modules.comment.domain.CommentLikeRepository;
import com.esgi.modules.comment.infrastructure.SpringDataCommentLikeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentLikeConfiguration {
    private final KernelConfiguration kernelConfiguration;

    public CommentLikeConfiguration(KernelConfiguration kernelConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
    }

    @Bean
    public CommentLikeRepository commentLikeRepository() {
        return new SpringDataCommentLikeRepository();
    }

    @Bean
    public LikeCommentEventListener likeCommentEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        LikeCommentEventListener listener = new LikeCommentEventListener();
        dispatcher.addListener(LikeCommentEvent.class, listener);
        return listener;
    }

    @Bean
    public LikeCommentCommandHandler likeCommentCommandHandler() {
        return new LikeCommentCommandHandler(commentLikeRepository(),
                kernelConfiguration.eventDispatcher(),
                kernelConfiguration.commandBus());
    }

    @Bean
    public CommandBus likeCommentCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(LikeComment.class, likeCommentCommandHandler());
        return commandBus;
    }

    @Bean
    public UnlikeCommentEventListener unlikeCommentEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        UnlikeCommentEventListener listener = new UnlikeCommentEventListener();
        dispatcher.addListener(UnlikeCommentEvent.class, listener);
        return listener;
    }

    @Bean
    public UnlikeCommentCommandHandler unlikeCommentCommandHandler() {
        return new UnlikeCommentCommandHandler(commentLikeRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus unlikeCommentCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(UnlikeComment.class, unlikeCommentCommandHandler());
        return commandBus;
    }

    @Bean
    public QueryBus likeCommentQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveLikedCommentsByUserId.class, new RetrieveLikedCommentsByUserIdHandler(commentLikeRepository()));
        queryBus.addHandler(IsCommentLikedQuery.class, new IsCommentLikedQueryHandler(commentLikeRepository()));
        queryBus.addHandler(CountCommentLikesQuery.class, new CountCommentLikesQueryHandler(commentLikeRepository()));
        return queryBus;
    }

    @Bean
    public RetrieveLikedCommentsByUserIdHandler retrieveLikedPCommentsByUserIdHandler() {
        return new RetrieveLikedCommentsByUserIdHandler(commentLikeRepository());
    }
}
