package com.esgi.modules.post.exposition;

import com.esgi.modules.user.domain.UserId;

import java.util.Date;

public class PostResponse {
    public String id;
    public String content;
    public UserId userId;
    public Date date;

    public PostResponse(String id, String content, UserId userId, Date date){
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", creatorId='" + userId + '\'' +
                ", date=" + date + '\'' +
                '}';
    }
}
