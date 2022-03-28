package com.esgi.kernel;

public interface EventDispatcher<E extends Event> {
    void addListener(Class<E> eventC, EventListener<E> listener);
    void dispatch(E event);
    void registerEvent(Class<E> eventC);
}

