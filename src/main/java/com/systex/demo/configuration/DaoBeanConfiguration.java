package com.systex.demo.configuration;

import com.systex.demo.dao.DataContentInterface;
import com.systex.demo.dao.KeyWordInterface;
import com.systex.demo.dao.UserInterface;
import com.systex.demo.dao.WebScrapingInterface;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DaoBeanConfiguration {

    @Bean
    public DataContentInterface dataContentDao(Jdbi jdbi) {
        return jdbi.onDemand(DataContentInterface.class);
    }

    @Bean
    public WebScrapingInterface webScrapingDao(Jdbi jdbi) {
        return jdbi.onDemand(WebScrapingInterface.class);
    }

    @Bean
    public KeyWordInterface keyWordDao(Jdbi jdbi) {
        return jdbi.onDemand(KeyWordInterface.class);
    }

    @Bean
    public UserInterface UserDao(Jdbi jdbi) {
        return jdbi.onDemand(UserInterface.class);
    }

}
