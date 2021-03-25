package org.hillel.service;

import org.hillel.Journey;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Component
public class TicketClient {

    private JourneyService journeyService;

    public TicketClient(@Qualifier("JDBCJourneyService") JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDateTime departure, LocalDateTime arrival) {
        if (Objects.isNull(stationFrom)) throw new IllegalArgumentException("stationFrom must be set!");
        if (Objects.isNull(stationTo)) throw new IllegalArgumentException("stationTo must be set!");
        if (Objects.isNull(departure)) throw new IllegalArgumentException("departure must be set!");
        if (Objects.isNull(arrival)) throw new IllegalArgumentException("arrival must be set!");
        return journeyService.find(stationFrom, stationTo, departure, arrival);
    }

}
