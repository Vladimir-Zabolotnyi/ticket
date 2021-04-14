package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.StopTimeEntity;
import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.repository.StopTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class TransactionalStopTimeService {

    @Autowired
    private StopTimeRepository stopTimeRepository;


    @Transactional
    public StopTimeEntity createOrUpdateStopTime(final StopTimeEntity stopTime) {
        if (Objects.isNull(stopTime)) throw new IllegalArgumentException("stopTime is null");
        return stopTimeRepository.createOrUpdate(stopTime);
    }

    @Transactional
    public void remove(StopTimeEntity stopTime) {
        if (Objects.isNull(stopTime)) throw new IllegalArgumentException("stopTime is null");
        stopTimeRepository.remove(stopTime);
    }

    @Transactional
    public void removeById(Long stopTimeId) {
        if (Objects.isNull(stopTimeId)) throw new IllegalArgumentException("stopTimeId is null");
        stopTimeRepository.removeById(stopTimeId);
    }
}
