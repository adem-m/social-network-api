package com.esgi.kernel;

import java.util.Map;

public class SimpleQueryBus implements QueryBus {
    private final Map<Class<? extends Query>, QueryHandler> dataMap;

    public SimpleQueryBus(Map<Class<? extends Query>, QueryHandler> dataMap) {
        this.dataMap = dataMap;
    }

    @Override
    public <Q extends Query, R> R send(Q query) {

        final QueryHandler queryHandler = dataMap.get(query.getClass());
        if (queryHandler == null) {
            throw new RuntimeException("No such query handler for " + query.getClass().getName());
        }

        return (R) queryHandler.handle(query);
    }
}
