package com.sls;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SlaveApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SlaveApplication.class).run(args);
    }
}
