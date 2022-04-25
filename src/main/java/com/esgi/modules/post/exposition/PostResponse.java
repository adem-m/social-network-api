package com.esgi.modules.post.exposition;

import com.esgi.modules.code.exposition.CodeResponse;

import java.time.LocalDateTime;
import java.util.Date;

public class PostResponse {
    public String id;
    public String content;
    public CodeResponse code;
    public String userId;
    public LocalDateTime date;

    public PostResponse(String id, String content, CodeResponse code, String userId, LocalDateTime date){
        this.id = id;
        this.content = content;
        this.code = code;
        this.userId = userId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", code='" + code + '\'' +
                ", creatorId='" + userId + '\'' +
                ", date=" + date + '\'' +
                '}';
    }
}
