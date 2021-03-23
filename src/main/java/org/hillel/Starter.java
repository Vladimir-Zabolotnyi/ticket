package org.hillel;

import org.hillel.config.RootConfig;
import org.hillel.context.AppContext;
import org.hillel.service.JourneyService;
import org.hillel.service.TicketClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;

public class Starter {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
//        AppContext.load("application.properties");
//        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);

        final ApplicationContext applicationContext = new ClassPathXmlApplicationContext("common-beans.xml");
        final TicketClient ticketClient = applicationContext.getBean(TicketClient .class);

        System.out.println(ticketClient.find("Odessa","Kiev",
              LocalDateTime.of(2021, Month.MARCH,23,19,10,25),
                LocalDateTime.of(2021, Month.MARCH,22,19,10,25)));
    }
}
