package com.esgi.modules.comment.application;

import com.esgi.kernel.Query;

public record CountCommentLikesQuery(String commentId) implements Query {
}
