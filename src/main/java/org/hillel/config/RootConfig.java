package org.hillel.config;

import org.hillel.service.JDBCJourneyServiceImpl;
import org.hillel.service.JourneyService;
import org.hillel.service.TicketClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.hillel.service")
public class RootConfig {

//@Bean
//    public TicketClient ticketClient(){
//    return new TicketClient(getJDBCJourneyService());
//}
//
//    @Bean("JDBCJourneyService")
//    public JourneyService getJDBCJourneyService(){
//        return new JDBCJourneyServiceImpl();
//    }
}
