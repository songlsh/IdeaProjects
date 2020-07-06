package com.sls.configuration;

import com.sls.applicationEvent.BlackListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class configuration {


    @Bean
    public BlackListService emailService() {

        BlackListService emailService = new BlackListService();
        // 在这里添加黑名单集合
        emailService.setList(Arrays.asList("known.spammer@example.org", "known.hacker@example.org", "john.doe@example.org", "blackAddredd@123.com"));

        return emailService;

    }
}
