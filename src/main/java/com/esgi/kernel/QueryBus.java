package com.esgi.kernel;

public interface QueryBus {
    <Q extends Query, R> R send(Q query);
}
