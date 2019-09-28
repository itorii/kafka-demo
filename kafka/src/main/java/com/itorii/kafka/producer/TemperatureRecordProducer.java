package com.itorii.kafka.producer;

import com.itorii.kafka.schema.TemperatureRecord;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class TemperatureRecordProducer extends AbstractProducer<TemperatureRecord>{

    public TemperatureRecordProducer(NewTopic topic, KafkaTemplate kafkaTemplate) {
        super(topic, kafkaTemplate);
    }

    @Override
    public void send(TemperatureRecord message){
        super.send(message);
    }
}
