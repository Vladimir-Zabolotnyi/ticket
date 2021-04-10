package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.persistence.entity.*;
import org.hillel.persistence.entity.enums.DirectionType;
import org.hillel.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Starter {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
//        AppContext.load("application.properties");
        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);

//        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("common-beans.xml");
        final TicketClient ticketClient = applicationContext.getBean(TicketClient.class);

        JourneyEntity journeyEntity = new JourneyEntity();
        journeyEntity.setStationFrom("Kiev");
        journeyEntity.setStationTo("Lvov");
        journeyEntity.setDateFrom(Instant.now());
        journeyEntity.setDateTo(Instant.now().plusMillis(100000000L));
        journeyEntity.setDirection(DirectionType.TO);
        journeyEntity.setActive(false);


        SeatEntity seatEntity = new SeatEntity();
        seatEntity.setCarriageNumber("1");
        seatEntity.setSeatNumber("1A");
        seatEntity.setSeatFree(false);
        seatEntity.setJourney(journeyEntity);

        SeatEntity seatEntity1 = new SeatEntity();
        seatEntity1.setCarriageNumber("12");
        seatEntity1.setSeatNumber("1A");
        seatEntity1.setSeatFree(true);
        seatEntity1.setJourney(journeyEntity);


        final VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setName("bus1");
        vehicleEntity.setYearOfBuilt(1987);
        vehicleEntity.setCountryOfBuilt("Germany");
        vehicleEntity.addSeat(seatEntity);
        vehicleEntity.addSeat(seatEntity1);

        journeyEntity.addVehicle(vehicleEntity);


        StopEntity stopEntity = new StopEntity();

        StopAdditionalInfoEntity stopAdditionalInfoEntity = new StopAdditionalInfoEntity();
        stopAdditionalInfoEntity.setLatitude(10D);
        stopAdditionalInfoEntity.setLongitude(176D);
        stopAdditionalInfoEntity.setCity("City1");
        stopAdditionalInfoEntity.setYearOfBuilt(1954);

        stopEntity.addStopAdditionalInfo(stopAdditionalInfoEntity);
        CommonInfo commonInfo = new CommonInfo();

        commonInfo.setName("stop 1");
        commonInfo.setDescription("stop 1 description");

        stopEntity.setCommonInfo(commonInfo);
        stopEntity.setApplyToJourneyBuild(stopEntity.isActive());



        StopTimeEntity stopTimeEntity = new StopTimeEntity();
        stopTimeEntity.setStopSequence(1);
        stopTimeEntity.setArrivalTime(Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(60L)));
        stopTimeEntity.setDepartureTime(Instant.now().plusMillis(TimeUnit.MINUTES.toMillis(65L)));
        stopTimeEntity.setStop(stopEntity);

        journeyEntity.addStopTime(stopTimeEntity);

        ticketClient.createOrUpdateJourney(journeyEntity);

        final Optional<JourneyEntity> journeyById = ticketClient.findJourneyById(journeyEntity.getId(), true);
        final JourneyEntity journey = journeyById.get();

        System.out.println("create journey with id= " + journey);

    }
}
