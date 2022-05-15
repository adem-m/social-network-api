package com.esgi.modules.post.application;

import com.esgi.kernel.*;
import com.esgi.modules.code.application.RetrieveCodeByPostId;
import com.esgi.modules.code.domain.Code;
import com.esgi.modules.post.domain.Post;
import com.esgi.modules.post.domain.PostId;
import com.esgi.modules.post.domain.PostRepository;

import java.time.LocalDateTime;

public final class EditPostCommandHandler implements CommandHandler<EditPost, PostId> {
    private final PostRepository postRepository;
    private final EventDispatcher<Event> eventEventDispatcher;
    private final QueryBus queryBus;

    public EditPostCommandHandler(PostRepository postRepository, EventDispatcher<Event> eventEventDispatcher, QueryBus queryBus) {
        this.postRepository = postRepository;
        this.eventEventDispatcher = eventEventDispatcher;
        this.queryBus = queryBus;
    }

    public PostId handle(EditPost editPost) {
        var postId = new PostId(editPost.postId);
        Post post = postRepository.findById(postId);
        if (!post.getUserId().getValue().equals(editPost.userId)) {
            throw new ForbiddenOperationException("You can't edit this post");
        }
        if (editPost.content != null && !editPost.content.equals("") && !post.getContent().equals(editPost.content))
            post.changeContent(editPost.content);
        if (editPost.code != null) {
            final Code code = (Code) queryBus.send(new RetrieveCodeByPostId(post.getId().getValue()));
            code.changeSource(editPost.code.source);
        }
        if ((editPost.content != null && !editPost.content.equals("")) || editPost.code != null) {
            post.changeLocalDateTime(LocalDateTime.now());
        }
        postRepository.add(post);
        eventEventDispatcher.dispatch(new EditPostEvent(postId));
        return postId;
    }
}
