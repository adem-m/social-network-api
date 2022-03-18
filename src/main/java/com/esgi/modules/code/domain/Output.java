package com.esgi.modules.code.domain;

public final class Output {
    private final String value;
    private final Status status;
    private final Duration duration;

    private Output(String value, Status status, Duration duration) {
        this.value = value;
        this.status = status;
        this.duration = duration;
    }

    public static Output success(String value, Duration duration) {
        return new Output(value, Status.SUCCESS, duration);
    }

    public static Output fail(String value, Duration duration) {
        return new Output(value, Status.ERROR, duration);
    }

    public String getValue() {
        return value;
    }

    public Status getStatus() {
        return status;
    }

    public Duration getDuration() {
        return duration;
    }
}
