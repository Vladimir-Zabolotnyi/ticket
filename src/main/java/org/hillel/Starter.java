package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.persistence.entity.*;
import org.hillel.persistence.entity.enums.DirectionType;
import org.hillel.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Starter {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
//        AppContext.load("application.properties");
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);

//        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("common-beans.xml");
        final TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        JourneyEntity journey1 = buildJourney("Kiev", "Lvov", Instant.now(), Instant.now().plusMillis(100000000L));
        journey1.addStopTime(builtStopTime(buildStop(10D,176D,"City1",1954,"stop1","desc1"),
                Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(60L)),Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(65L)),1));
        VehicleEntity vehicle1 = buildVehicle("bus1",1987,"Germany");
        journey1.addVehicle(vehicle1);
        journey1.addSeat(buildSeat(vehicle1,false,"1","1A"));
        journey1 = ticketClient.createOrUpdateJourney(journey1);

//        final Optional<JourneyEntity> journeyById = ticketClient.findJourneyById(journey1.getId(), true);
//        final JourneyEntity journey = journeyById.get();
//
//        System.out.println("create journey with id= " + journey);
//
//
//
//
//        SeatEntity seatEntity = new SeatEntity();
//        seatEntity.setCarriageNumber("1");
//        seatEntity.setSeatNumber("1A");
//        seatEntity.setSeatFree(false);
//
//        SeatEntity seatEntity1 = new SeatEntity();
//        seatEntity1.setCarriageNumber("12");
//        seatEntity1.setSeatNumber("1A");
//        seatEntity1.setSeatFree(true);


//        final VehicleEntity vehicleEntity = new VehicleEntity();
//        vehicleEntity.setName("bus1");
//        vehicleEntity.setYearOfBuilt(1987);
//        vehicleEntity.setCountryOfBuilt("Germany");
//        vehicleEntity.addSeat(seatEntity);
//        vehicleEntity.addSeat(seatEntity1);
//
//        journeyEntity.addVehicle(vehicleEntity);
//        journeyEntity.addSeat(seatEntity);
//        journeyEntity.addSeat(seatEntity1);

//        StopTimeEntity stopTimeEntity = new StopTimeEntity();
//        stopTimeEntity.setStopSequence(1);
//        stopTimeEntity.setArrivalTime(Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(60L)));
//        stopTimeEntity.setDepartureTime(Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(65L)));
//        stopTimeEntity.setStop(stopEntity);
//
//        journeyEntity.addStopTime(stopTimeEntity);
    }

    private static JourneyEntity buildJourney(final String stationFrom, final String stationTo, final Instant dateFrom, final Instant dateTo) {
        if (StringUtils.isEmpty(stationFrom)) throw new IllegalArgumentException("stationFrom must be set!");
        if (StringUtils.isEmpty(stationTo)) throw new IllegalArgumentException("stationTo must be set!");
        if (Objects.isNull(dateFrom)) throw new IllegalArgumentException("dateFrom must be set!");
        if (Objects.isNull(dateTo)) throw new IllegalArgumentException("dateTo must be set!");
        final JourneyEntity journey = new JourneyEntity();
        journey.setStationFrom(stationFrom);
        journey.setStationTo(stationTo);
        journey.setDateFrom(dateFrom);
        journey.setDateTo(dateTo);
        journey.setDirection(DirectionType.TO);
        journey.setActive(true);
        return journey;
    }

    private static StopEntity buildStop(final Double latitude, final Double longitude, final String city, final Integer yearOfBuilt, final String name, final String description) {
        if (Objects.isNull(latitude)) throw new IllegalArgumentException("latitude must be set!");
        if (Objects.isNull(longitude)) throw new IllegalArgumentException("longitude must be set!");
        if (Objects.isNull(city)) throw new IllegalArgumentException("city must be set!");
        if (Objects.isNull(yearOfBuilt)) throw new IllegalArgumentException("yearOfBuilt must be set!");
        if (StringUtils.isEmpty(name)) throw new IllegalArgumentException("name must be set!");
        if (StringUtils.isEmpty(description)) throw new IllegalArgumentException("description must be set!");

        StopAdditionalInfoEntity stopAdditionalInfo = new StopAdditionalInfoEntity();
        stopAdditionalInfo.setLatitude(10D);
        stopAdditionalInfo.setLongitude(176D);
        stopAdditionalInfo.setCity("City1");
        stopAdditionalInfo.setYearOfBuilt(1954);

        CommonInfo commonInfo = new CommonInfo();
        commonInfo.setName(name);
        commonInfo.setDescription(description);

        StopEntity stop = new StopEntity();
        stop.setCommonInfo(commonInfo);
        stop.addStopAdditionalInfo(stopAdditionalInfo);
        return stop;
    }

    private static VehicleEntity buildVehicle(final String name, final Integer yearOfBuilt, final String countryOfBuilt) {
        if (StringUtils.isEmpty(name)) throw new IllegalArgumentException("name must be set!");
        if (Objects.isNull(yearOfBuilt)) throw new IllegalArgumentException("yearOfBuilt must be set!");
        if (StringUtils.isEmpty(countryOfBuilt)) throw new IllegalArgumentException("countryOfBuilt must be set!");

        final VehicleEntity vehicle = new VehicleEntity();
        vehicle.setName(name);
        vehicle.setYearOfBuilt(yearOfBuilt);
        vehicle.setCountryOfBuilt(countryOfBuilt);

        return vehicle;
    }

    private static StopTimeEntity builtStopTime( final StopEntity stop, final Instant arrivalTime, final Instant departureTime, final Integer stopSequence) {
        if (Objects.isNull(stop)) throw new IllegalArgumentException("stop must be set!");
        if (Objects.isNull(arrivalTime)) throw new IllegalArgumentException("arrivalTime must be set!");
        if (Objects.isNull(departureTime)) throw new IllegalArgumentException("departureTime must be set!");
        if (Objects.isNull(stopSequence)) throw new IllegalArgumentException("stopSequence must be set!");

        final StopTimeEntity stopTime = new StopTimeEntity();
        stopTime.setStopSequence(stopSequence);
        stopTime.setArrivalTime(arrivalTime);
        stopTime.setDepartureTime(departureTime);
        stopTime.setStop(stop);

        return stopTime;
    }

    private static SeatEntity buildSeat(final VehicleEntity vehicle, final boolean free, final String carriageNumber, final String seatNumber) {
    if (Objects.isNull(vehicle)) throw new IllegalArgumentException("vehicle must be set!");
        if (Objects.isNull(free)) throw new IllegalArgumentException("whether free must be decided!");
        if (Objects.isNull(carriageNumber)) throw new IllegalArgumentException("carriageNumber must be set!");
        if (Objects.isNull(seatNumber)) throw new IllegalArgumentException("seatNumber must be set!");

        final SeatEntity seat = new SeatEntity();
        seat.setCarriageNumber(carriageNumber);
        seat.setSeatNumber(seatNumber);
        seat.setSeatFree(free);
      seat.setVehicle(vehicle);

        return seat;
    }


}
