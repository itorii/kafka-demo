package com.itorii.kafkaDemo.producerApp.controller;

public interface KafkaController<T> {
    void publish(T message);
}
