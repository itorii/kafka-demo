package com.itorii.kafka.consumer;

import java.util.concurrent.CountDownLatch;

public interface AbstractConsumer<T> {

    CountDownLatch getLatch();

    void consume(T message);

}
