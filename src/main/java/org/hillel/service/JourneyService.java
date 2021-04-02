package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;

public interface JourneyService {


    @Transactional
    Long createJourney(JourneyEntity journeyEntity);

    Collection<Journey> find(final String stationFrom, final String stationTo, final LocalDateTime departure,
                             final LocalDateTime arrival);
}
