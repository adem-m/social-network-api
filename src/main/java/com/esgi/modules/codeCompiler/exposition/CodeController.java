package com.esgi.modules.codeCompiler.exposition;

import com.esgi.kernel.CommandBus;
import com.esgi.modules.codeCompiler.application.RunCode;
import com.esgi.modules.codeCompiler.domain.Code;
import com.esgi.modules.codeCompiler.domain.Language;
import com.esgi.modules.codeCompiler.domain.Output;
import com.esgi.modules.file.application.CreateFile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
final public class CodeController {
    private final CommandBus commandBus;

    public CodeController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping(
            path = "/run-code",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
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
}
