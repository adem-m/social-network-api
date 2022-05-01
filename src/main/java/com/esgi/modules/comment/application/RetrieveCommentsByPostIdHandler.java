package com.esgi.modules.comment.application;

import com.esgi.kernel.QueryHandler;
import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentRepository;
import com.esgi.modules.post.domain.PostId;

import java.util.List;

public class RetrieveCommentsByPostIdHandler implements QueryHandler<RetrieveCommentsByPostId, List<Comment>> {
    private final CommentRepository commentRepository;

    public RetrieveCommentsByPostIdHandler(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> handle(RetrieveCommentsByPostId query) {
        PostId postId = new PostId(query.id);
        return commentRepository.findByPostId(postId);
    }
}
