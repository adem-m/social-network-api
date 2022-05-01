package com.esgi.modules.code.infrastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.code.domain.Code;
import com.esgi.modules.code.domain.CodeId;
import com.esgi.modules.code.domain.CodeRepository;
import com.esgi.modules.user.domain.UserId;
import com.esgi.modules.user.infrastructure.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class SpringDataCodeRepository implements CodeRepository {
    @Autowired
    private JpaCodeRepository jpaCodeRepository;
    @Autowired
    private CodeMapper codeMapper;

    @Override
    public CodeId nextIdentity() {
        return new CodeId(UUID.randomUUID().toString());
    }

    @Override
    public Code findById(CodeId id) throws NoSuchEntityException {
        return jpaCodeRepository.findById(id.getValue())
                .map(codeMapper::toModel)
                .orElseThrow(() -> NoSuchEntityException.withId(id));
    }

    @Override
    public void add(Code entity) {
        jpaCodeRepository.save(codeMapper.toEntity(entity));
    }

    @Override
    public void delete(CodeId id) {
        jpaCodeRepository.deleteById(id.getValue());
    }

    @Override
    public List<Code> findAll() {
        return jpaCodeRepository.findAll().stream().map(codeMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Code findByPostId(String id) {
        CodeEntity codeEntity = jpaCodeRepository.findByPostId(id);
        return codeEntity == null ? null : codeMapper.toModel(codeEntity);
    }
}

@Repository
interface JpaCodeRepository extends JpaRepository<CodeEntity, String> {
    CodeEntity findByPostId(String id);
}
