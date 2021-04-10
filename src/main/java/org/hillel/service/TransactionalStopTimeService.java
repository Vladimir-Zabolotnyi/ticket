package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.StopTimeEntity;
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
}
