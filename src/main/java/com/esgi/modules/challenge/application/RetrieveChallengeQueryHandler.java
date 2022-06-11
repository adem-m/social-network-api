package com.esgi.modules.challenge.application;

import com.esgi.kernel.QueryBus;
import com.esgi.kernel.QueryHandler;
import com.esgi.modules.challenge.domain.ChallengeEntryRepository;
import com.esgi.modules.code.application.RetrieveCodeById;
import com.esgi.modules.code.domain.Code;
import com.esgi.modules.code.domain.CodeId;
import com.esgi.modules.user.domain.UserId;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
final public class RetrieveChallengeQueryHandler implements QueryHandler<RetrieveChallengeQuery, List<Code>> {
    private final QueryBus queryBus;
    private final ChallengeEntryRepository repository;

    public RetrieveChallengeQueryHandler(QueryBus queryBus, ChallengeEntryRepository repository) {
        this.queryBus = queryBus;
        this.repository = repository;
    }

    @Override
    public List<Code> handle(RetrieveChallengeQuery query) {
        log.info("Retrieving challenge for user {}", query.userId());
        List<CodeId> codeIds = repository.findCodeIdsByUserId(new UserId(query.userId()));
        if (codeIds.isEmpty()) return List.of();
        return codeIds.stream().map(codeId -> (Code) queryBus.send(new RetrieveCodeById(codeId))).toList();
    }
}
