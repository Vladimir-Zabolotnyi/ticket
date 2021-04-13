package org.hillel.service;

import org.hillel.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

    public Optional<JourneyEntity> findJourneyById(Long id, boolean withDependencies) {
        return id == null ? Optional.empty() : journeyService.findById(id, withDependencies);
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
        return vehicleService.createOrUpdateVehicle(vehicle);
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


    public void removeJourney(JourneyEntity journey) {
        journeyService.remove(journey);
    }

    public void removeJourneyById(Long journeyId) {
        journeyService.removeById(journeyId);
    }

    public void removeVehicle(VehicleEntity vehicle) {
        vehicleService.remove(vehicle);
    }

    public void removeVehicleById(Long vehicleId) {
        vehicleService.removeById(vehicleId);
    }

    public void removeStop(StopEntity stop) {
        stopService.remove(stop);
    }

    public void removeStopById(Long stopId) {
        stopService.removeByID(stopId);
    }

    public void removeStopTime(StopTimeEntity stopTime) {
        stopTimeService.remove(stopTime);
    }

    public void removeStopTimeById(Long stopTimeId) {
        stopTimeService.removeById(stopTimeId);
    }

    public void removeSeat(SeatEntity seatEntity) {
        seatService.remove(seatEntity);
    }

    public void removeSeatById(Long seatId) {
        seatService.removeById(seatId);
    }
}
