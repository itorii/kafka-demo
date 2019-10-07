package com.itorii.kafkaDemo.common.events;

public class BaseEvent<T> {

    private T payload;

    public BaseEvent(T payload) {
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
