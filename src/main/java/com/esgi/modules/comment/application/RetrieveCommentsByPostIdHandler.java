package com.esgi.modules.comment.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentRepository;
import com.esgi.modules.post.domain.PostId;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RetrieveCommentsByPostIdHandler implements QueryHandler<RetrieveCommentsByPostId, List<Comment>> {
    private final CommentRepository commentRepository;

    public RetrieveCommentsByPostIdHandler(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> handle(RetrieveCommentsByPostId query) {
        PostId postId = new PostId(query.id);
        log.info("Retrieving comments for post {}", postId.getValue());
        return commentRepository.findByPostId(postId);
    }
}
