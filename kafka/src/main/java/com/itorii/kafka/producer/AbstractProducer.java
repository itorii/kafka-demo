package com.itorii.kafka.producer;

public interface AbstractProducer<T> {

    void send(T message) throws Exception;


}
