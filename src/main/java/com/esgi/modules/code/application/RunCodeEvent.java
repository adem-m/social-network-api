package com.esgi.modules.code.application;

import com.esgi.kernel.ApplicationEvent;
import com.esgi.modules.code.domain.Code;

public record RunCodeEvent(Code code) implements ApplicationEvent {
}
