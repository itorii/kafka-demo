package com.itorii.kafkaDemo.producerApp.events;

import com.itorii.kafkaDemo.common.events.BaseEvent;
import com.itorii.kafkaDemo.producerApp.producer.TemperatureRecordProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SensorDataListener {

    private TemperatureRecordProducer temperatureRecordProducer;
    private static final Logger log = LoggerFactory.getLogger(SensorDataListener.class);

    public SensorDataListener(TemperatureRecordProducer temperatureRecordProducer) {
        this.temperatureRecordProducer = temperatureRecordProducer;
    }

    @EventListener
    public void onSensorData(BaseEvent<String> event){
        log.info("Received sensor data {}", event.getPayload());
        //todo parse and push to kafka
    }
}

