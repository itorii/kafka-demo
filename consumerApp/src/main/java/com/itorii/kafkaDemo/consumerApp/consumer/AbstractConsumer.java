package com.itorii.kafkaDemo.consumerApp.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public abstract class AbstractConsumer<T> {

    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    private CountDownLatch latch = new CountDownLatch(9);

    public CountDownLatch getLatch() {
        return this.latch;
    }

    public void consume(ConsumerRecord<String,T> consumerRecord) {
        log.info("received message: '{}'", consumerRecord.value() );
        latch.countDown();
    }

}
