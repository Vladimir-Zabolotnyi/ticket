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
    @Qualifier("transactionalJourneyService")
    private JourneyService journeyService;

    @Autowired
    private TransactionalStopService stopService;

    @Autowired
    private TransactionalStopTimeService stopTimeService;

    @Autowired
    private TransactionalStopAdditionalInfoService stopAdditionalInfoService;

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


    public Long createJourney(final JourneyEntity journey) {
        if (Objects.isNull(journey)) throw new IllegalArgumentException("journey is null");
        return journeyService.createJourney(journey);
    }

    public Optional<JourneyEntity> getJourneyById(Long id, boolean withDependecies) {
//        Assert.notNull(id,"id must be set");
        return id == null ? Optional.empty() : journeyService.getById(id, withDependecies);
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDateTime departure, LocalDateTime arrival) {
        if (Objects.isNull(stationFrom)) throw new IllegalArgumentException("stationFrom must be set!");
        if (Objects.isNull(stationTo)) throw new IllegalArgumentException("stationTo must be set!");
        if (Objects.isNull(departure)) throw new IllegalArgumentException("departure must be set!");
        if (Objects.isNull(arrival)) throw new IllegalArgumentException("arrival must be set!");
        return journeyService.find(stationFrom, stationTo, departure, arrival);
    }

    public void saveJourney(JourneyEntity journey) {
        if (Objects.isNull(journey)) throw new IllegalArgumentException("journey is null");
        journeyService.save(journey);
    }


    public Long createStop(final StopEntity stop) {
        if (Objects.isNull(stop)) throw new IllegalArgumentException("stop is null");
        return stopService.createStop(stop);
    }

    public void saveStop(final StopEntity stop) {
        if (Objects.isNull(stop)) throw new IllegalArgumentException("stop is null");
        stopService.save(stop);
    }

    public Long createStopAdditionalInfo(final StopAdditionalInfoEntity stopAdditionalInfo) {
        if (Objects.isNull(stopAdditionalInfo)) throw new IllegalArgumentException("stopAdditionalInfo is null");
        return stopAdditionalInfoService.createStopAdditionalInfo(stopAdditionalInfo);
    }

    public void saveStopAdditionalInfo(final StopAdditionalInfoEntity stopAdditionalInfo) {
        if (Objects.isNull(stopAdditionalInfo)) throw new IllegalArgumentException("stopAdditionalInfo is null");
        stopAdditionalInfoService.save(stopAdditionalInfo);
    }

    public Long createStopTime(final StopTimeEntity stopTime) {
        if (Objects.isNull(stopTime)) throw new IllegalArgumentException("stopTime is null");
        return stopTimeService.createStopTime(stopTime);
    }

    public void saveStopTime(final StopTimeEntity stopTime) {
        if (Objects.isNull(stopTime)) throw new IllegalArgumentException("stopTime is null");
        stopTimeService.save(stopTime);
    }

    public Long createVehicle(final VehicleEntity vehicle) {
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle is null");
        return vehicleService.createVehicle(vehicle);
    }

    public void saveVehicle(final VehicleEntity vehicle) {
        if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle is null");
        vehicleService.save(vehicle);
    }

    public Long createSeat(final SeatEntity seat) {
        if (Objects.isNull(seat)) throw new IllegalArgumentException("seat is null");
        return seatService.createSeat(seat);
    }

    public void saveSeat(final SeatEntity seat) {
        if (Objects.isNull(seat)) throw new IllegalArgumentException("seat is null");
        seatService.save(seat);
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
