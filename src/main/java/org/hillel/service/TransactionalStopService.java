package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.hillel.persistence.repository.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Service
public class TransactionalStopService {
    @Autowired
   private StopRepository stopRepository;


    @Transactional
    public Long createStop(final StopEntity stopEntity) {
        if (Objects.isNull(stopEntity)) throw new IllegalArgumentException("stopEntity is null");
        return stopRepository.create(stopEntity);
    }

}
