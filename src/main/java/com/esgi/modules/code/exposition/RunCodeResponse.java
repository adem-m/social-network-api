package com.esgi.modules.code.exposition;

public final class RunCodeResponse {
    public String value;
    public String status;
    public long duration;

    public RunCodeResponse(String value, String status, long duration) {
        this.value = value;
        this.status = status;
        this.duration = duration;
    }
}
