package com.github.olegik1719.learnup.operasales.lesson17.config;

import com.github.olegik1719.learnup.operasales.lesson17.repository.EventRepo;
import com.github.olegik1719.learnup.operasales.lesson17.repository.OperaRepo;
import com.github.olegik1719.learnup.operasales.lesson17.repository.TicketRepo;
import com.github.olegik1719.learnup.operasales.lesson17.repository.simple.SimpleEventRepo;
import com.github.olegik1719.learnup.operasales.lesson17.repository.simple.SimpleOperaRepo;
import com.github.olegik1719.learnup.operasales.lesson17.repository.simple.SimpleTicketRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    OperaRepo operaRepo() {
        return new SimpleOperaRepo();
    }
    @Bean
    EventRepo eventRepo() {
        return new SimpleEventRepo();
    }
    @Bean
    TicketRepo ticketRepo() {
        return new SimpleTicketRepo();
    }
}
