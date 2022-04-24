package com.esgi.modules.code.domain;

import com.esgi.kernel.Repository;
import com.esgi.modules.post.domain.PostId;

import java.util.List;

public interface CodeRepository extends Repository<CodeId, Code> {
    List<Code> findAll();

    Code findByPostId(String id);
}
