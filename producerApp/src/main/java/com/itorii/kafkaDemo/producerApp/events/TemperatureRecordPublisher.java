package com.itorii.kafkaDemo.producerApp.events;

import com.itorii.kafkaDemo.common.events.BaseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TemperatureRecordPublisher {

    private ApplicationEventPublisher publisher;
    private static final Logger log = LoggerFactory.getLogger(TemperatureRecordPublisher.class);

    public TemperatureRecordPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void fireEvent(String temperatureRecord){
        publisher.publishEvent(new BaseEvent<>(temperatureRecord));
        log.info("Publshed temperature record: {}", temperatureRecord);
    }



}
