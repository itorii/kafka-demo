package com.itorii.kafkaDemo.common.events;

import com.itorii.kafkaDemo.common.avro.TemperatureRecord;

public class TemperatureRecordEvent extends BaseEvent<TemperatureRecord> {
    public TemperatureRecordEvent(TemperatureRecord payload) {
        super(payload);
    }
}
