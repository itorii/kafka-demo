package com.itorii.kafka.producer;

import com.itorii.kafka.schema.TemperatureRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class TemperatureRecordProducer implements AbstractProducer<TemperatureRecord>{
    private static final Logger log = LoggerFactory.getLogger(TemperatureRecordProducer.class);

    private KafkaTemplate kafkaTemplate;

    public TemperatureRecordProducer(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(TemperatureRecord message){
        log.info("sending message: '{}'", message);
        ListenableFuture<SendResult<String, TemperatureRecord>> f = kafkaTemplate.send("temperature",message);
        f.addCallback(new ListenableFutureCallback<SendResult<String, TemperatureRecord>>() {
            @Override
            public void onSuccess(SendResult<String, TemperatureRecord> result) {
                log.info("Sent message {} with offset {}", message, result);
            }
            @Override
            public void onFailure(Throwable e) {
                log.info("Couldn't send message {} reason: {}", message, e.getMessage());
            }
        });
    }
}
