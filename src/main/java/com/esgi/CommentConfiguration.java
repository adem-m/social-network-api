package com.esgi;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.comment.application.*;
import com.esgi.modules.comment.domain.CommentRepository;
import com.esgi.modules.comment.infrastructure.SpringDataCommentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentConfiguration {
    private final KernelConfiguration kernelConfiguration;

    public CommentConfiguration(KernelConfiguration kernelConfiguration) {
        this.kernelConfiguration = kernelConfiguration;
    }

    @Bean
    public CommentRepository commentRepository() {
        return new SpringDataCommentRepository();
    }

    @Bean
    public CreateCommentEventListener createCommentEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        CreateCommentEventListener listener = new CreateCommentEventListener();
        dispatcher.addListener(CreateCommentEvent.class, listener);
        return listener;
    }

    @Bean
    public CreateCommentCommandHandler createCommentCommandHandler() {
        return new CreateCommentCommandHandler(commentRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus createCommentCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(CreateComment.class, createCommentCommandHandler());
        return commandBus;
    }

    @Bean
    public EditCommentEventListener editCommentEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        EditCommentEventListener listener = new EditCommentEventListener();
        dispatcher.addListener(EditCommentEvent.class, listener);
        return listener;
    }

    @Bean
    public EditCommentCommandHandler editCommentCommandHandler() {
        return new EditCommentCommandHandler(commentRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus editCommentCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(EditComment.class, editCommentCommandHandler());
        return commandBus;
    }

    @Bean
    public DeleteCommentEventListener deleteCommentEventListener() {
        EventDispatcher dispatcher = this.kernelConfiguration.eventDispatcher();
        DeleteCommentEventListener listener = new DeleteCommentEventListener();
        dispatcher.addListener(DeleteCommentEvent.class, listener);
        return listener;
    }

    @Bean
    public DeleteCommentCommandHandler deleteCommentCommandHandler() {
        return new DeleteCommentCommandHandler(commentRepository(), kernelConfiguration.eventDispatcher());
    }

    @Bean
    public CommandBus deleteCommentCommandBus() {
        final CommandBus commandBus = kernelConfiguration.commandBus();
        commandBus.addHandler(DeleteComment.class, deleteCommentCommandHandler());
        return commandBus;
    }

    @Bean
    public QueryBus commentByIdQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveCommentById.class, new RetrieveCommentByIdHandler(commentRepository(), kernelConfiguration.queryBus()));
        return queryBus;
    }

    @Bean
    public RetrieveCommentByIdHandler retrieveCommentByIdHandler() {
        return new RetrieveCommentByIdHandler(commentRepository(), kernelConfiguration.queryBus());
    }

    @Bean
    public QueryBus commentsByPostIdQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveCommentsByPostId.class, new RetrieveCommentsByPostIdHandler(commentRepository(), kernelConfiguration.queryBus()));
        return queryBus;
    }

    @Bean
    public RetrieveCommentsByPostIdHandler retrieveCommentsByPostIdHandler() {
        return new RetrieveCommentsByPostIdHandler(commentRepository(), kernelConfiguration.queryBus());
    }

    @Bean
    public QueryBus commentsQueryBus() {
        final QueryBus queryBus = kernelConfiguration.queryBus();
        queryBus.addHandler(RetrieveComments.class, new RetrieveCommentsHandler(commentRepository(), kernelConfiguration.queryBus()));
        return queryBus;
    }

    @Bean
    public RetrieveCommentsHandler retrieveCommentsHandler() {
        return new RetrieveCommentsHandler(commentRepository(), kernelConfiguration.queryBus());
    }
}
