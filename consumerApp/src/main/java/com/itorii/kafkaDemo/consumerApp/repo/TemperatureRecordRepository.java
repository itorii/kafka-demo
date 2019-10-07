package com.itorii.kafkaDemo.consumerApp.repo;

import com.itorii.kafkaDemo.common.entities.TemperatureRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemperatureRecordRepository extends MongoRepository<TemperatureRecord, String> {

}
