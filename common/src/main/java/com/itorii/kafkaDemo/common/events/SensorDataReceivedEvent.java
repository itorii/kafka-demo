package com.itorii.kafkaDemo.common.events;

public class SensorDataReceivedEvent extends BaseEvent<String> {

    public SensorDataReceivedEvent(String payload) {
        super(payload);
    }
}
