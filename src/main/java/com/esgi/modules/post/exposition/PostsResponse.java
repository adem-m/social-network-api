package com.esgi.modules.post.exposition;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@SuppressWarnings("all")
@XmlRootElement
public class PostsResponse {
    public final List<PostResponse> posts;

    public PostsResponse(List<PostResponse> posts) {
            this.posts = posts;
        }
}
