package com.esgi.modules.post.exposition;

import com.esgi.kernel.CoreUserResponse;
import com.esgi.modules.code.exposition.CodeResponse;

public class PostResponse {
    public String id;
    public String content;
    public CodeResponse code;
    public CoreUserResponse creator;
    public String date;
    public long likes;
    public boolean isLiked;

    public PostResponse(String id, String content, CodeResponse code, CoreUserResponse creator, String date, long likes, boolean isLiked) {
        this.id = id;
        this.content = content;
        this.code = code;
        this.creator = creator;
        this.date = date;
        this.likes = likes;
        this.isLiked = isLiked;
    }

    @Override
    public String toString() {
        return "PostResponse{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", code=" + code +
                ", creator=" + creator +
                ", date='" + date + '\'' +
                ", likes=" + likes +
                ", isLiked=" + isLiked +
                '}';
    }
}
