package com.esgi.modules.post.exposition;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@SuppressWarnings("all")
@XmlRootElement
public class PostsLikeResponse {
    public final List<PostLikeResponse> postsLiked;

    public PostsLikeResponse(List<PostLikeResponse> postsLiked) {
            this.postsLiked = postsLiked;
        }
}
