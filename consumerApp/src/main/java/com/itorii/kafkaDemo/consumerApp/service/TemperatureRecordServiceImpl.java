package com.itorii.kafkaDemo.consumerApp.service;

import com.itorii.kafkaDemo.common.entities.TemperatureRecord;
import com.itorii.kafkaDemo.common.exception.ServiceException;
import com.itorii.kafkaDemo.consumerApp.repo.TemperatureRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TemperatureRecordServiceImpl implements TemperatureRecordService {

    private TemperatureRecordRepository repo;
    private static final Logger log = LoggerFactory.getLogger(TemperatureRecordServiceImpl.class);

    public TemperatureRecordServiceImpl(TemperatureRecordRepository repo) {
        this.repo = repo;
    }

    public TemperatureRecord insert(TemperatureRecord record) {
        try {
            return repo.insert(record);
        } catch (Exception e) {
            throw new ServiceException("Couldn't save record ", e);
        }
    }

    public List<TemperatureRecord> findAll() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            throw new ServiceException("Couldn't find records ", e);
        }
    }

    public Page<TemperatureRecord> findAllPaged(Pageable pageable) {
        try {
            return repo.findAll(pageable);
        } catch (Exception e) {
            throw new ServiceException("Couldn't find records ", e);
        }
    }
}
