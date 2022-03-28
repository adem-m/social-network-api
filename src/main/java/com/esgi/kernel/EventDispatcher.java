package com.esgi.kernel;

public interface EventDispatcher<E extends Event> {
    void dispatch(E event);
}

