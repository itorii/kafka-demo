package com.itorii.kafka.consumer;

import com.itorii.kafka.schema.TemperatureRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
@Component
public class TemperatureRecordConsumer implements AbstractConsumer<TemperatureRecord> {
    private static final Logger log = LoggerFactory.getLogger(TemperatureRecordConsumer.class);

    private CountDownLatch latch = new CountDownLatch(9);

    @Override
    public CountDownLatch getLatch(){
        return this.latch;
    }

    @Override
    @KafkaListener(topics = "temperature")
    public void consume(TemperatureRecord message) {
        log.info("received message: '{}'", message);
        latch.countDown();
    }
}
