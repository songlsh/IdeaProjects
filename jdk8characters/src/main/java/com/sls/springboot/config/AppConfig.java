package com.sls.springboot.config;


import com.sls.bean.Person;
import org.springframework.context.annotation.Bean;


public class AppConfig {

    @Bean
    public Person getPerson(){
        return  new Person("lisa",15);
    }
}
