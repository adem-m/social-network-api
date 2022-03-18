package com.esgi.modules.code.exposition;

import com.esgi.modules.code.application.CodeService;
import com.esgi.modules.code.domain.Code;
import com.esgi.modules.code.domain.Language;
import com.esgi.modules.code.domain.Output;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
final public class CodeController {
    final private CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping(
            path = "/run-code",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RunCodeResponse> runCode(@RequestBody @Valid RunCodeRequest request) {
        Code code = new Code(request.code, Language.fromString(request.language));
        Output output = codeService.execute(code);
        RunCodeResponse response = new RunCodeResponse(
                output.getValue(),
                output.getStatus().name(),
                output.getDuration().value());
        return ResponseEntity.ok(response);
    }
}
