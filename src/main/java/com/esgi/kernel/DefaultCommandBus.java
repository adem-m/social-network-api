package com.esgi.kernel;

import java.util.Map;

public class DefaultCommandBus<TCommand extends Command, R> implements CommandBus<TCommand, R> {
    private final Map<Class<TCommand>, CommandHandler> handlers;

    public DefaultCommandBus(Map<Class<TCommand>, CommandHandler> dataMap) {
        this.handlers = dataMap;
    }

    @Override
    public void addHandler(Class<TCommand> commandC, CommandHandler handler) {
        this.handlers.putIfAbsent(commandC, handler);
    }

    @Override
    public R send(TCommand command) {
        return dispatch(command);
    }

    private R dispatch(TCommand command) {
        final CommandHandler commandHandler = handlers.get(command.getClass());
        if (commandHandler == null) {
            throw new RuntimeException("No such command handler for " + command.getClass().getName());
        }

        return (R) commandHandler.handle(command);
    }
}