package com.itorii.kafkaDemo.producerApp.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public abstract class AbstractProducer<T> {

    private static final Logger log = LoggerFactory.getLogger(TemperatureRecordProducer.class);

    private NewTopic topic;
    private KafkaTemplate kafkaTemplate;

    public AbstractProducer(NewTopic topic, KafkaTemplate kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @SuppressWarnings("unchecked")
    void send(T message) {

        log.info("sending message to topic: {} '{}'", topic.name(), message);

        ListenableFuture<SendResult<String, T>> f = (ListenableFuture) kafkaTemplate.send(topic.name(), message);

        f.addCallback(new ListenableFutureCallback<SendResult<String, T>>() {
            @Override
            public void onSuccess(SendResult<String, T> result) {
                log.info("Sent message {} with offset {}", message, result.getProducerRecord().value());
            }

            @Override
            public void onFailure(Throwable e) {
                log.info("Couldn't send message {} reason: {}", message, e.getMessage());
            }
        });

    }

    ;


}
