package com.itorii.kafkaDemo.consumerApp.service;

import com.itorii.kafkaDemo.common.entities.TemperatureRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TemperatureRecordService {
    TemperatureRecord insert(TemperatureRecord record);

    List<TemperatureRecord> findAll();

    Page<TemperatureRecord> findAllPaged(Pageable pageable);
}
