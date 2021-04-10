package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class TicketClient  /* implements DisposableBean implements InitializingBean */ {

//    @Autowired()
//    @Qualifier("JDBCJourneyService")
//    private JourneyService journeyService;

    @Autowired
    private TransactionalJourneyService journeyService;

    @Autowired
    private TransactionalStopService stopService;

    @Autowired
    private TransactionalStopTimeService stopTimeService;

    @Autowired
    private TransactionalVehicleService vehicleService;

    @Autowired
    private TransactionalSeatService seatService;

    @Value("${system.message:journeyService init}")
    private String systemMessage;

//    @Autowired
//private Environment environment;

    public TicketClient() {
    }


    public JourneyEntity createOrUpdateJourney(final JourneyEntity journey) {
        if (Objects.isNull(journey)) throw new IllegalArgumentException("journey is null");
        return journeyService.createOrUpdateJourney(journey);
    }

    public Optional<JourneyEntity> findJourneyById(Long id, boolean withDependecies) {
        return id == null ? Optional.empty() : journeyService.findById(id, withDependecies);
    }

    public StopEntity createOrUpdateStop(final StopEntity stop) {
        if (Objects.isNull(stop)) throw new IllegalArgumentException("stop is null");
        return stopService.createOrUpdateStop(stop);
    }

       public StopTimeEntity createOrUpdateStopTime(final StopTimeEntity stopTime) {
        if (Objects.isNull(stopTime)) throw new IllegalArgumentException("stopTime is null");
        return stopTimeService.createOrUpdateStopTime(stopTime);
    }

    public VehicleEntity createOrUpdateVehicle(final VehicleEntity vehicle) {
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle is null");
        return vehicleService.createOrUodateVehicle(vehicle);
    }

    public SeatEntity createOrUpdateSeat(final SeatEntity seat) {
        if (Objects.isNull(seat)) throw new IllegalArgumentException("seat is null");
        return seatService.createOrUpdateSeat(seat);
    }



    @PostConstruct
    public void journeyServiceInit() throws Exception {
        if (journeyService == null) {
            throw new IllegalStateException("journeySevice not init");
        } else {
            System.out.println(systemMessage);
        }
    }

    @PreDestroy
    public void destroy() throws Exception {
        System.out.println("bean destroyed");
    }


}
