package com.esgi.modules.codeCompiler.application;

import com.esgi.kernel.CommandBus;
import com.esgi.kernel.CommandHandler;
import com.esgi.kernel.QueryBus;
import com.esgi.modules.code.application.RetrieveCodeById;
import com.esgi.modules.code.domain.Code;
import com.esgi.modules.codeCompiler.domain.ChallengeOutput;
import com.esgi.modules.codeCompiler.domain.Language;
import com.esgi.modules.codeCompiler.domain.Output;

import java.util.List;

public record RunChallengeCommandHandler(
        QueryBus queryBus, CommandBus commandBus) implements CommandHandler<RunChallenge, List<ChallengeOutput>> {
    public List<ChallengeOutput> handle(RunChallenge command) {
        List<Code> codes = command.codeIds().stream()
                .map(id -> (Code) queryBus.send(new RetrieveCodeById(id))).toList();
        return codes.stream()
                .map(code ->
                        {
                            com.esgi.modules.codeCompiler.domain.Code compilerCode =
                                    new com.esgi.modules.codeCompiler.domain.Code(code.getSource(),
                                            Language.fromString(code.getLanguage()));
                            Output output = (Output) commandBus.send(
                                    new RunCode(compilerCode));
                            return new ChallengeOutput(compilerCode, output);
                        }
                ).toList();
    }
}
