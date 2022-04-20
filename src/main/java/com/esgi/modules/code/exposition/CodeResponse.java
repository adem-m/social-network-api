package com.esgi.modules.code.exposition;

import com.esgi.modules.codeCompiler.domain.Language;
import com.esgi.modules.post.domain.PostId;

public class CodeResponse {
    public String id;
    public PostId postId;
    public String source;
    public Language language;

    public CodeResponse(String id, PostId postId, String source, Language language){
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
