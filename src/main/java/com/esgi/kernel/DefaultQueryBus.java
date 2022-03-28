package com.esgi.kernel;

import java.util.Map;

public class DefaultQueryBus<TQuery extends Query, R> implements QueryBus<TQuery, R> {
    private final Map<Class<TQuery>, QueryHandler> handlers;

    public DefaultQueryBus(Map<Class<TQuery>, QueryHandler> dataMap) {
        this.handlers = dataMap;
    }

    @Override
    public void addHandler(Class<TQuery> queryC, QueryHandler handler) {
        this.handlers.putIfAbsent(queryC, handler);
    }

    @Override
    public R send(TQuery query) {
        return dispatch(query);
    }

    private R dispatch(TQuery query) {
        final QueryHandler queryHandler = handlers.get(query.getClass());
        if (queryHandler == null) {
            throw new RuntimeException("No such query handler for " + query.getClass().getName());
        }

        return (R) queryHandler.handle(query);
    }
}
