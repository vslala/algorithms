package com.bma.algorithms.tries;

public interface StringST<Value> {
    void put(String key, Value val);

    boolean contains(String key);

    Value get(String key);

    void delete(String key);
}
