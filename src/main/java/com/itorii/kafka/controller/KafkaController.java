package com.itorii.kafka.controller;

public interface KafkaController<T> {
    void publish(T message);
}
