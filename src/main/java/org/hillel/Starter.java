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
import java.util.Objects;


public class Starter {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
//        AppContext.load("application.properties")
//        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("common-beans.xml");

        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
        final TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

//        System.out.println(ticketClient.findJourneyById(1L,true));
//
//        System.out.println( ticketClient.findAllAsNamedSeats());
//        System.out.println( ticketClient.findAllAsNamedVehicles());
//        System.out.println( ticketClient.findAllAsNamedStops());
//        System.out.println( ticketClient.findAllAsCriteriaStopsTime());
        System.out.println( ticketClient.findAllAsCriteriaJourneys());

//
//        VehicleEntity vehicle1 = buildVehicle("bus1", 1987, "Germany");
//        vehicle1 = ticketClient.createOrUpdateVehicle(vehicle1);
//
//        VehicleEntity vehicle2 = buildVehicle("bus12", 1987, "Germany");
//        vehicle2 = ticketClient.createOrUpdateVehicle(vehicle2);
//        VehicleEntity vehicle3 = buildVehicle("bus134", 1987, "Germany");
//        vehicle3 = ticketClient.createOrUpdateVehicle(vehicle3);
//        System.out.println(ticketClient.findVehicleById(1L));
//        System.out.println(ticketClient.findVehicleByIds(1L,2L,3L,4L,5L,6L));


//        SeatEntity seat = buildSeat(vehicle1,false,"12","22");
//        ticketClient.createOrUpdateSeat(seat);
//
//        JourneyEntity journey1 = buildJourney("Kiev", "Lvov", Instant.now(), Instant.now().plusMillis(100000000L));
//        journey1.addVehicle(vehicle1);
//        journey1=ticketClient.createOrUpdateJourney(journey1);
//
//        journey1.addSeat(seat);
//
//        ticketClient.createOrUpdateJourney(journey1);
//
//        StopEntity stop1=buildStop(1D,2D,"s",1923,"dsd","dsd");
//        ticketClient.createOrUpdateStop(stop1);
//
//        StopTimeEntity stopTimeEntity=builtStopTime(stop1,Instant.now(), Instant.now().plusMillis(100000000L),1);
//        ticketClient.createOrUpdateStopTime(stopTimeEntity);
//
//        journey1.addStopTime(stopTimeEntity);
//        ticketClient.createOrUpdateJourney(journey1);

//        ticketClient.removeStop(stop1);


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

    private static StopTimeEntity builtStopTime(final StopEntity stop, final Instant arrivalTime, final Instant departureTime, final Integer stopSequence) {
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
