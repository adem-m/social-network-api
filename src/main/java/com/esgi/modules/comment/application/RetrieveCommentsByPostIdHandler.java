package com.esgi.modules.comment.application;

import com.esgi.kernel.QueryBus;
import com.esgi.kernel.QueryHandler;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentRepository;
import com.esgi.modules.comment.domain.FullComment;
import com.esgi.modules.post.domain.PostId;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RetrieveCommentsByPostIdHandler implements QueryHandler<RetrieveCommentsByPostId, List<FullComment>> {
    private final CommentRepository commentRepository;
    private final QueryBus queryBus;

    public RetrieveCommentsByPostIdHandler(CommentRepository commentRepository, QueryBus queryBus) {
        this.commentRepository = commentRepository;
        this.queryBus = queryBus;
    }

    @Override
    public List<FullComment> handle(RetrieveCommentsByPostId query) {
        PostId postId = new PostId(query.id);
        log.info("Retrieving comments for post {}", postId.getValue());
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(
                comment -> {
                    long likes = (Long) queryBus.send(new CountCommentLikesQuery(comment.getCommentId().getValue()));
                    return new FullComment(comment, likes);
                }
        ).toList();
    }
}
