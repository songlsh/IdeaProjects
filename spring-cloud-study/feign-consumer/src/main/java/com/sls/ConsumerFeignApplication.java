package com.sls;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerFeignApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ConsumerFeignApplication.class).run(args);
    }

}
