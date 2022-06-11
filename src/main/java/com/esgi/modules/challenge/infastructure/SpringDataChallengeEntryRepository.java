package com.esgi.modules.challenge.infastructure;

import com.esgi.kernel.NoSuchEntityException;
import com.esgi.modules.challenge.domain.ChallengeEntry;
import com.esgi.modules.challenge.domain.ChallengeEntryId;
import com.esgi.modules.challenge.domain.ChallengeEntryRepository;
import com.esgi.modules.code.domain.CodeId;
import com.esgi.modules.user.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class SpringDataChallengeEntryRepository implements ChallengeEntryRepository {
    @Autowired
    private JpaChallengeEntryRepository repository;

    @Override
    public ChallengeEntryId nextIdentity() {
        return new ChallengeEntryId(UUID.randomUUID().toString());
    }

    @Override
    public ChallengeEntry findById(ChallengeEntryId id) throws NoSuchEntityException {
        return repository
                .findById(id.value())
                .map(ChallengeEntryMapper::toDomain)
                .orElseThrow(() -> new NoSuchEntityException(id.value()));
    }

    @Override
    public void add(ChallengeEntry entry) {
        repository.save(ChallengeEntryMapper.toEntity(entry));
    }

    @Override
    public void delete(ChallengeEntryId id) {
        repository.deleteById(id.value());
    }

    @Override
    public List<CodeId> findCodeIdsByUserId(UserId userId) {
        return repository
                .findCodeIdsByUserId(userId.getValue())
                .stream().map(CodeId::new)
                .toList();
    }

    @Override
    public ChallengeEntry findByCodeIdAndUserId(CodeId codeId, UserId userId) {
        ChallengeEntryEntity entity = repository.findByCodeIdAndUserId(codeId.getValue(), userId.getValue());
        return entity == null ? null : ChallengeEntryMapper.toDomain(entity);
    }
}

@Repository
interface JpaChallengeEntryRepository extends JpaRepository<ChallengeEntryEntity, String> {
    @Query("SELECT e.codeId FROM ChallengeEntryEntity e WHERE e.userId = ?1")
    List<String> findCodeIdsByUserId(String userId);

    ChallengeEntryEntity findByCodeIdAndUserId(String codeId, String userId);
}
