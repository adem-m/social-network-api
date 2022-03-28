package com.esgi.modules.infrastructure;

import com.esgi.kernel.Event;
import com.esgi.kernel.EventDispatcher;
import com.esgi.kernel.EventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultEventDispatcher<E extends Event> implements EventDispatcher<E> {
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
            System.out.println("Dispatched " + event.getClass().getSimpleName() + " event.");
            eventListeners.forEach(e -> e.listenTo(event));
        }
    }

    @Override
    public void registerEvent(Class<E> eventC) {
        this.eventListeners.putIfAbsent(eventC, new ArrayList<>());
    }
}