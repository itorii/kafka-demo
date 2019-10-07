package com.itorii.kafkaDemo.producerApp.config;

import com.itorii.kafkaDemo.common.avro.TemperatureRecord;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {

    @Value("${kafka.server}")
    private String kafkaServer;

    @Value("${offset.reset}")
    private String offsetResetConfig;

    @Value("${schema.registry.url}")
    private String schemaRegistryUrl;

    @Value("${consumer.group.id}")
    private String groupId;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> adminConfig = new HashMap<>();
        adminConfig.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        adminConfig.put(AdminClientConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, 1000);
        return new KafkaAdmin(adminConfig);
    }

    @Bean
    public NewTopic temperatureTopic() {
        return new NewTopic("temperature", 1, (short) 1);
    }

    @Bean
    public ProducerFactory<String, TemperatureRecord> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerProperties());
    }

    @Bean
    public Map<String, Object> producerProperties() {
        Map<String, Object> producerProperties = new HashMap<>();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        producerProperties.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        return producerProperties;
    }

    @Bean
    public KafkaTemplate<String, TemperatureRecord> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
