package org.hillel.config;

import org.hillel.service.JourneyService;
import org.hillel.service.TicketClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("org.hillel")
@PropertySource({"application.properties","database.properties"})
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
