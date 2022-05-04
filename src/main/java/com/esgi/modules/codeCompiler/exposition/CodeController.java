package com.esgi.modules.codeCompiler.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.modules.code.domain.CodeId;
import com.esgi.modules.codeCompiler.application.RunChallenge;
import com.esgi.modules.codeCompiler.application.RunCode;
import com.esgi.modules.codeCompiler.domain.Code;
import com.esgi.modules.codeCompiler.domain.Language;
import com.esgi.modules.codeCompiler.domain.Output;
import com.esgi.modules.file.application.CreateFile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/run-code")
final public class CodeController {
    private final CommandBus commandBus;

    public CodeController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RunCodeResponse> runCode(@RequestBody @Valid RunCodeRequest request) {
        Code code = new Code(request.code, Language.fromString(request.language));
        CreateFile createFile = new CreateFile(code.language().getSourceName(), code.source());
        commandBus.send(createFile);
        RunCode runCode = new RunCode(code);
        Output output = (Output) commandBus.send(runCode);
        RunCodeResponse response = new RunCodeResponse(
                output.getValue(),
                output.getStatus().name(),
                output.getDuration().value());
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/challenge", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RunChallengeResponse> runCodeChallenge(@RequestBody @Valid RunChallengeRequest request) {
        RunChallenge runChallenge = new RunChallenge(request.codeIds.stream().map(CodeId::new).toList());
        List<ChallengeOutput> outputs = (List<ChallengeOutput>) commandBus.send(runChallenge);
        RunChallengeResponse response = new RunChallengeResponse(outputs);
        return ResponseEntity.ok(response);
    }
}
