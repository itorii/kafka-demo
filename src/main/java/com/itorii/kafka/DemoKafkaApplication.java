package com.itorii.kafka;

import com.itorii.kafka.consumer.TemperatureRecordConsumer;
import com.itorii.kafka.entities.TemperatureRecord;
import com.itorii.kafka.producer.TemperatureRecordProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DemoKafkaApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoKafkaApplication.class, args);
	}
}
