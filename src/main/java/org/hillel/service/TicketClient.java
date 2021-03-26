package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class TicketClient  /* implements DisposableBean implements InitializingBean */  {

    @Autowired()
    @Qualifier("JDBCJourneyService")
    private JourneyService journeyService;

    @Autowired
    @Qualifier("transactionalJourneyService")
    private JourneyService transactionalJourneyService;

@Value("${system.message:property not found}")
    private String systemMessage;


//    @Autowired
//private Environment environment;

//    @Autowired
//    @Qualifier("JDBCJourneyService")
//    public void setJourneyService(JourneyService journeyService) {
//        this.journeyService = journeyService;
//    }

    public TicketClient() {
    }

    public Long createJourney(final JourneyEntity journeyEntity){
        return transactionalJourneyService.createJourney(journeyEntity);
    }

    public Collection<Journey> find(String stationFrom, String stationTo, LocalDateTime departure, LocalDateTime arrival) {
        if (Objects.isNull(stationFrom)) throw new IllegalArgumentException("stationFrom must be set!");
        if (Objects.isNull(stationTo)) throw new IllegalArgumentException("stationTo must be set!");
        if (Objects.isNull(departure)) throw new IllegalArgumentException("departure must be set!");
        if (Objects.isNull(arrival)) throw new IllegalArgumentException("arrival must be set!");
        return journeyService.find(stationFrom, stationTo, departure, arrival);
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
