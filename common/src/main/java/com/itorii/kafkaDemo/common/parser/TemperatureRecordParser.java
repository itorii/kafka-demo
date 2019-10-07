package com.itorii.kafkaDemo.common.parser;

import com.itorii.kafkaDemo.common.avro.TemperatureRecord;

import java.util.Date;
import java.util.UUID;

public class TemperatureRecordParser {

    public static TemperatureRecord fromString(String record){
        String[] recordArray = record.split("[A-z]:");
        return TemperatureRecord.newBuilder()
                .setDeviceId(UUID.randomUUID().toString())
                .setTemperature(recordArray[0])//add humidity to the model?
                .setTimestamp(String.valueOf(new Date().getTime()))
                .build();
    }
}
