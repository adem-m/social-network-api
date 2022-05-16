package com.esgi.modules.challenge.application;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.QueryBus;
import com.esgi.kernel.QueryHandler;
import com.esgi.modules.challenge.domain.ChallengeEntryRepository;
import com.esgi.modules.challenge.domain.ChallengeOutput;
import com.esgi.modules.code.application.RetrieveCodeById;
import com.esgi.modules.code.domain.Code;
import com.esgi.modules.code.domain.CodeId;
import com.esgi.modules.codeCompiler.application.RunCode;
import com.esgi.modules.codeCompiler.domain.Language;
import com.esgi.modules.codeCompiler.domain.Output;
import com.esgi.modules.user.domain.UserId;

import java.util.List;

public record RunChallengeQueryHandler(
        CommandBus commandBus,
        QueryBus queryBus,
        ChallengeEntryRepository repository) implements QueryHandler<RunChallengeQuery, List<ChallengeOutput>> {
    @Override
    public List<ChallengeOutput> handle(RunChallengeQuery command) {
        List<CodeId> codeIds = repository.findCodeIdsByUserId(new UserId(command.userId()));
        List<Code> codes = codeIds.stream().map(codeId -> (Code) queryBus.send(new RetrieveCodeById(codeId))).toList();
        return codes.stream().map(code -> {
                    com.esgi.modules.codeCompiler.domain.Code mappedCode = new com.esgi.modules.codeCompiler.domain.Code(
                            code.getSource(),
                            Language.fromString(code.getLanguage()));
                    Output output = (Output) commandBus.send(
                            new RunCode(mappedCode)
                    );
                    return new ChallengeOutput(mappedCode, output);
                }
        ).toList();
    }
}
