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
import com.esgi.modules.codeCompiler.domain.Duration;
import com.esgi.modules.codeCompiler.domain.Language;
import com.esgi.modules.codeCompiler.domain.Output;
import com.esgi.modules.file.application.CreateFile;
import com.esgi.modules.user.domain.UserId;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public record RunChallengeQueryHandler(
        CommandBus commandBus,
        QueryBus queryBus,
        ChallengeEntryRepository repository) implements QueryHandler<RunChallengeQuery, List<ChallengeOutput>> {
    private final static int CODE_RUNS_COUNT = 3;

    @Override
    public List<ChallengeOutput> handle(RunChallengeQuery query) {
        List<CodeId> codeIds = repository.findCodeIdsByUserId(new UserId(query.userId()));
        if (codeIds.isEmpty()) throw new EmptyChallengeQueueException();
        List<Code> codes = codeIds.stream().map(codeId -> (Code) queryBus.send(new RetrieveCodeById(codeId))).toList();
        log.info("Challenge ran");
        return codes.stream().map(code -> {
                    com.esgi.modules.codeCompiler.domain.Code mappedCode = new com.esgi.modules.codeCompiler.domain.Code(
                            code.getSource(),
                            Language.fromString(code.getLanguage()));
                    CreateFile createFile = new CreateFile(mappedCode.language().getSourceName(), mappedCode.source());
                    commandBus.send(createFile);
                    List<Output> outputs = new ArrayList<>();
                    for (int i = 0; i < CODE_RUNS_COUNT; i++) {
                        outputs.add((Output) commandBus.send(new RunCode(mappedCode)));
                    }
                    Duration durationSum = outputs.stream()
                            .map(Output::getDuration)
                            .reduce((duration, duration2) -> new Duration(duration.value() + duration2.value()))
                            .get();
                    Output output = outputs.get(0);
                    output.setDuration(new Duration(durationSum.value() / outputs.size()));
                    return new ChallengeOutput(
                            mappedCode,
                            output,
                            isOutputCompliant(query.expectedOutput(), output.getValue())
                    );
                }
        ).toList();
    }

    private boolean isOutputCompliant(String expectedOutput, String actualOutput) {
        if (expectedOutput == null || expectedOutput.isEmpty()) return true;
        return expectedOutput.trim().equals(actualOutput.trim());
    }
}
