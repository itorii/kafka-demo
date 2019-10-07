package com.itorii.kafkaDemo.consumerApp.consumer;

import com.itorii.kafkaDemo.common.avro.TemperatureRecord;
import com.itorii.kafkaDemo.consumerApp.events.TemperatureRecordPublisher;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class TemperatureRecordConsumer extends AbstractConsumer<TemperatureRecord> {

    private CountDownLatch latch = new CountDownLatch(9);

    private TemperatureRecordPublisher temperatureRecordPublisher;

    public TemperatureRecordConsumer(TemperatureRecordPublisher temperatureRecordPublisher) {
        this.temperatureRecordPublisher = temperatureRecordPublisher;
    }

    @Override
    public CountDownLatch getLatch(){
        return this.latch;
    }

    @Override
    @KafkaListener(topics = "temperature")
    public void consume(ConsumerRecord<String, TemperatureRecord> temperatureRecord) {
        super.consume(temperatureRecord);
        temperatureRecordPublisher.fireEvent(temperatureRecord.value());
    }
}
