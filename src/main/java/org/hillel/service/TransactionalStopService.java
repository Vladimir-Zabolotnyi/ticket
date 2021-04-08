package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopAdditionalInfoEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.hillel.persistence.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Service
public class TransactionalStopService {
    @Autowired
   private StopRepository stopRepository;


    @Transactional
    public Long createStop(final StopEntity stop) {
        if (Objects.isNull(stop)) throw new IllegalArgumentException("stop is null");
        return stopRepository.create(stop);
    }

    @Transactional
    public void save(final StopEntity stop) {
        if (Objects.isNull(stop)) throw new IllegalArgumentException("stop is null");
        stopRepository.save(stop);
    }
}
