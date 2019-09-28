package com.itorii.kafka.producer;

import com.itorii.kafka.schema.TemperatureRecord;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TemperatureRecordProducer extends AbstractProducer<TemperatureRecord>{

    public TemperatureRecordProducer(NewTopic topic, KafkaTemplate kafkaTemplate) {
        super(topic, kafkaTemplate);
    }

    @Override
    public void send(com.itorii.kafka.schema.TemperatureRecord message){
        super.send(message);
    }
}
