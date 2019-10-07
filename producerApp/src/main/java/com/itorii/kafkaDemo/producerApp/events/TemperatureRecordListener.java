package com.itorii.kafkaDemo.producerApp.events;

import com.itorii.kafkaDemo.common.events.BaseEvent;
import com.itorii.kafkaDemo.producerApp.producer.TemperatureRecordProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TemperatureRecordListener {

    private TemperatureRecordProducer temperatureRecordProducer;
    private static final Logger log = LoggerFactory.getLogger(TemperatureRecordListener.class);

    public TemperatureRecordListener(TemperatureRecordProducer temperatureRecordProducer) {
        this.temperatureRecordProducer = temperatureRecordProducer;
    }

    @EventListener
    public void onTemperatureRecord(BaseEvent<String> event){
        log.info("Received temperature record {}", event.getPayload());
        //todo parse and push to kafka
    }
}

