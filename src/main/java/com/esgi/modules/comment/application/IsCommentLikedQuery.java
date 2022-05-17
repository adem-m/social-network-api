package com.esgi.modules.comment.application;

import com.esgi.kernel.Query;

public record IsCommentLikedQuery(String commentId, String userId) implements Query {
}
