package com.esgi.modules.codeCompiler.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.codeCompiler.domain.Code;

public record RunCodeEvent(Code code) implements ApplicationEvent {
}
