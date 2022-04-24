package com.esgi.modules.code.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.code.domain.Code;
import com.esgi.modules.code.domain.CodeId;
import com.esgi.modules.code.domain.CodeRepository;
import com.esgi.modules.post.domain.PostId;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class InMemoryCodeRepository implements CodeRepository {
    private final AtomicInteger count = new AtomicInteger(0);

    private final Map<CodeId, Code> data = new ConcurrentHashMap<>();

    @Override
    public CodeId nextIdentity() {
        return new CodeId(count.incrementAndGet());
    }

    @Override
    public Code findById(CodeId id) {
        final Code code = data.get(id);
        if (code == null) {
            throw NoSuchEntityException.withId(id);
        }
        return code;
    }

    @Override
    public void add(Code code) {
        data.put(code.getCodeId(), code);
    }

    @Override
    public void delete(CodeId id) {
        data.remove(id);
    }

    @Override
    public List<Code> findAll() {
        return List.copyOf(data.values());
    }

    @Override
    public Code findByPostId(String id) {
        if (data.values().stream().noneMatch(code -> code.getPostId().getValue().equals(id))){
            return null;
        }
        return data.values().stream().filter(code -> code.getPostId().getValue().equals(id)).collect(Collectors.toList()).get(0);
    }
}
