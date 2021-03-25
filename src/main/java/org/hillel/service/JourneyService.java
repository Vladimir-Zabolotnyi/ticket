package org.hillel.service;

import org.hillel.Journey;

import java.time.LocalDateTime;
import java.util.Collection;

public interface JourneyService {


    Collection<Journey> find(final String stationFrom, final String stationTo, final LocalDateTime departure,
                             final LocalDateTime arrival);
}
