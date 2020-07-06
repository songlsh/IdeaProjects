package com.eureka.study;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

@EnableEurekaServer
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = new SpringApplicationBuilder(Application.class).run(args);
      //  ConfigurableApplicationContext run1 = SpringApplication.run(Application.class, args);
    }
}
