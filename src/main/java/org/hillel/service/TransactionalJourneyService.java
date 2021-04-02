package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.repository.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Service(value = "transactionalJourneyService")
public class TransactionalJourneyService implements JourneyService  {
    @Autowired
    private JourneyRepository journeyRepository;


    @Transactional
    @Override
    public Long createJourney(final JourneyEntity journeyEntity) {
        if (Objects.isNull(journeyEntity)) throw new IllegalArgumentException("journeyEntity is null");
        return journeyRepository.create(journeyEntity);
    }

    @Override
    public Collection<Journey> find(String stationFrom, String stationTo, LocalDateTime departure, LocalDateTime arrival) {
        return null;
    }
}
