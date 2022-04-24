package com.esgi.kernel;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public final class DefaultEventDispatcher<E extends Event> implements EventDispatcher<E> {
    private final Map<Class<E>, List<EventListener<E>>> eventListeners;

    public DefaultEventDispatcher(Map<Class<E>, List<EventListener<E>>> eventListeners) {
        this.eventListeners = eventListeners;
    }

    @Override
    public void addListener(Class<E> eventC, EventListener<E> eventListener) {
        final List<EventListener<E>> eventListeners = this.eventListeners.get(eventC);
        if (eventListeners == null) {
            throw new RuntimeException(eventC.getSimpleName() + " event was not registered.");
        }
        eventListeners.add(eventListener);
    }

    @Override
    public void dispatch(E event) {
        final List<EventListener<E>> eventListeners = this.eventListeners.get(event.getClass());
        if (eventListeners != null) {
            log.info("Dispatched {} event.", event.getClass().getSimpleName());
            eventListeners.forEach(e -> e.listenTo(event));
        }
    }

    @Override
    public void registerEvent(Class<E> eventC) {
        this.eventListeners.putIfAbsent(eventC, new ArrayList<>());
    }
}