package org.example.config;

import org.example.InitContactsFromFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("init")
public class InitAppConfig {

    @Bean
    public InitContactsFromFile initContactsFromFile(){
        return new InitContactsFromFile();
    }
}
