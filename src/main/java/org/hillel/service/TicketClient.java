package org.hillel.service;

import org.hillel.Journey;
import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.StopEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class TicketClient  /* implements DisposableBean implements InitializingBean */  {

//    @Autowired()
//    @Qualifier("JDBCJourneyService")
//    private JourneyService journeyService;

    @Autowired
    @Qualifier("transactionalJourneyService")
    private JourneyService journeyService;

    @Autowired
    private TransactionalStopService stopService;


    @Value("${system.message:journeyService init}")
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
        return journeyService.createJourney(journeyEntity);
    }

    public  Optional<JourneyEntity> getJourneyById(Long id,boolean withDependecies){
//        Assert.notNull(id,"id must be set");
    return  id ==null  ? Optional.empty(): journeyService.getById(id,withDependecies);
    }

    public Long createStop(final StopEntity stopEntity){
        return stopService.createStop(stopEntity);
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

    public void saveJourney(JourneyEntity journey) {
        //todo
        journeyService.save(journey);
    }
}
