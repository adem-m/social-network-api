package com.esgi.kernel;

public interface QueryBus<TQuery extends Query, R> {
    void addHandler(Class<TQuery> queryC, QueryHandler handler);
    R send(TQuery query);
}