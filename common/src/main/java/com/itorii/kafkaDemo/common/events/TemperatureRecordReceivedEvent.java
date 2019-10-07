package com.itorii.kafkaDemo.common.events;

public class TemperatureRecordReceivedEvent extends BaseEvent<String> {

    public TemperatureRecordReceivedEvent(String payload) {
        super(payload);
    }
}
