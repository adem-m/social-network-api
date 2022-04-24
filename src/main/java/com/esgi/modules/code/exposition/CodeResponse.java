package com.esgi.modules.code.exposition;

public class CodeResponse {
    public String id;
    public String postId;
    public String source;
    public String language;

    public CodeResponse(String id, String postId, String source, String language){
        this.id = id;
        this.postId = postId;
        this.source = source;
        this.language = language;
    }

    @Override
    public String toString() {
        return "CodeDTO{" +
                "id=" + id +
                ", postId='" + postId + '\'' +
                ", source='" + source + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
