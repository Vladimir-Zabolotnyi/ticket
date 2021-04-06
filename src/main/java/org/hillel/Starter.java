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
import java.util.Optional;

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

        final VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setName("bus1");
        journeyEntity.addVehicle(vehicleEntity);


        StopAdditionalInfoEntity stopAdditionalInfoEntity = new StopAdditionalInfoEntity();
        stopAdditionalInfoEntity.setLatitude(10D);
        stopAdditionalInfoEntity.setLongitude(176D);

        StopEntity stopEntity = new StopEntity();
        stopEntity.addStopAdditionalInfo(stopAdditionalInfoEntity);

        CommonInfo commonInfo = new CommonInfo();
        commonInfo.setName("stop 1");
        commonInfo.setDescription("stop 1 description");

        stopEntity.setCommonInfo(commonInfo);

        stopEntity.setApplyToJourneyBuild(stopEntity.isActive());
        journeyEntity.addStop(stopEntity);
        ticketClient.createJourney(journeyEntity);
//        ticketClient.createStop(stopEntity);
        final Optional<JourneyEntity> journeyById = ticketClient.getJourneyById(journeyEntity.getId(), true);
        final JourneyEntity journey = journeyById.get();
        System.out.println("get all stops by journey " + journey.getStops());
        System.out.println("create journey with id= " + journeyById);
        journey.setDirection(DirectionType.FROM);
        ticketClient.saveJourney(journey);


    }
}
