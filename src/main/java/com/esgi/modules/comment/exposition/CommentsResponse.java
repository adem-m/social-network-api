package com.esgi.modules.comment.exposition;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@SuppressWarnings("all")
@XmlRootElement
public class CommentsResponse {
    public final List<CommentResponse> comments;

    public CommentsResponse(List<CommentResponse> comments) {
            this.comments = comments;
        }
}
