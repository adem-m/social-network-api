package com.esgi.modules.comment.infrastructure;

import com.esgi.modules.comment.domain.Comment;
import com.esgi.modules.comment.domain.CommentId;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.user.domain.UserId;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentEntity toEntity(Comment comment) {
        return new CommentEntity(
                comment.id().getValue(),
                comment.getPostId().getValue(),
                comment.getContent(),
                comment.getUserId().getValue(),
                comment.getDate());
    }

    public Comment toModel(CommentEntity commentEntity) {
        return new Comment(
                new CommentId(commentEntity.getId()),
                new PostId(commentEntity.getPostId()),
                commentEntity.getContent(),
                new UserId(commentEntity.getUserId()),
                commentEntity.getDate());
    }
}
