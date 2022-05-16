package com.esgi.modules.challenge.application;

public class EmptyChallengeQueueException extends IllegalStateException {
    public EmptyChallengeQueueException() {
        super("Cannot run an empty challenge queue");
    }
}
