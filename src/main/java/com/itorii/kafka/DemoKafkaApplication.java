package com.itorii.kafka;

import com.itorii.kafka.consumer.TemperatureRecordConsumer;
import com.itorii.kafka.entities.TemperatureRecord;
import com.itorii.kafka.producer.TemperatureRecordProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DemoKafkaApplication {
	public static void main(String[] args) {
		Logger log  = LoggerFactory.getLogger(DemoKafkaApplication.class);
		AnnotationConfigApplicationContext c = (AnnotationConfigApplicationContext) SpringApplication.run(DemoKafkaApplication.class, args);
		TemperatureRecordProducer producer = c.getBean(TemperatureRecordProducer.class);
		TemperatureRecordConsumer consumer = c.getBean(TemperatureRecordConsumer.class);
		try{
			while(true){
				//producer.send(new TemperatureRecord());
				//consumer.getLatch().await(10L, TimeUnit.SECONDS);
				//todo remove and register resources
			}
		} catch (Throwable t){
			log.error("Kafka demo error occured. reason: {}", t.getMessage());
		}
	}
}
