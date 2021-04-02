package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.persistence.entity.CommonInfo;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopAdditionalInfoEntity;
import org.hillel.persistence.entity.StopEntity;
import org.hillel.persistence.entity.enums.DirectionType;
import org.hillel.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.time.Instant;

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

        ticketClient.createStop(stopEntity);
        System.out.println("create journey with id= " + ticketClient.createJourney(journeyEntity));
    }
}
