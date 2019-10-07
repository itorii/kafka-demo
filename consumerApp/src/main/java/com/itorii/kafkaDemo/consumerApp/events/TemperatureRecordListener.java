package com.itorii.kafkaDemo.consumerApp.events;

import com.itorii.kafkaDemo.common.avro.TemperatureRecord;
import com.itorii.kafkaDemo.consumerApp.service.TemperatureRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TemperatureRecordListener {

    private TemperatureRecordService temperatureRecordService;
    private static final Logger logger = LoggerFactory.getLogger(TemperatureRecordListener.class);

    public TemperatureRecordListener(TemperatureRecordService temperatureRecordService) {
        this.temperatureRecordService = temperatureRecordService;
    }

    @EventListener
    public void onTemperatureRecord(TemperatureRecord record) {
        temperatureRecordService.insert(fromAvro(record));
        logger.info("ON TEMPERATURE RECORD processed");
    }

    private com.itorii.kafkaDemo.common.entities.TemperatureRecord fromAvro(TemperatureRecord record) {
        return new com.itorii.kafkaDemo.common.entities.TemperatureRecord(
                record.getDeviceId().toString(),
                record.getTemperature().toString(),
                record.getTimestamp().toString());
    }
}
