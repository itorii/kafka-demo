package com.itorii.kafka.controller;

import com.itorii.kafka.entities.TemperatureRecord;
import com.itorii.kafka.entities.converter.TemperatureRecordConverter;
import com.itorii.kafka.producer.TemperatureRecordProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature")
public class TemperatureRecordController {

    private TemperatureRecordProducer temperatureRecordProducer;
    private static final Logger log = LoggerFactory.getLogger(TemperatureRecordController.class);

    public TemperatureRecordController(TemperatureRecordProducer temperatureRecordProducer) {
        this.temperatureRecordProducer = temperatureRecordProducer;
    }

    @PostMapping("/publish")
    public void publish(@RequestBody TemperatureRecord temperatureRecord){
        if(temperatureRecord != null){
            try{
                this.temperatureRecordProducer.send(TemperatureRecordConverter.convert(temperatureRecord));
            } catch (Exception e){
                log.error("failed to publish temperature record {}", e.getCause());
            }
        }

    }




}
