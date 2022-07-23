package com.esgi.modules.healthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Ouais tu veux quoi ??";
    }
}
