package com.itorii.kafkaDemo.consumerApp.events;

import com.itorii.kafkaDemo.common.avro.TemperatureRecord;
import com.itorii.kafkaDemo.common.events.BaseEvent;
import com.itorii.kafkaDemo.common.events.TemperatureRecordEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TemperatureRecordPublisher {
    private ApplicationEventPublisher publisher;

    private static final Logger log = LoggerFactory.getLogger(TemperatureRecordPublisher.class);

    public TemperatureRecordPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void fireEvent(TemperatureRecord temperatureRecord){
        publisher.publishEvent(new TemperatureRecordEvent(temperatureRecord));
        log.info("Published temperature record: {}", temperatureRecord);
    }



}
