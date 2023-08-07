package com.universityteam.flashmemorizer.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper;
    }
    @Bean
    public Logger logger(){
        return LoggerFactory.getLogger(getClass());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
