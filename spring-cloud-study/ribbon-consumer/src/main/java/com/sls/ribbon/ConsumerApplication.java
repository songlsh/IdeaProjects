package com.sls.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@EnableCircuitBreaker  //开启断路器功能
//@EnableEurekaClient    // 开启注册发现服务
//@SpringBootApplication  // springboot注解（启动拉起  @SpringBootConfiguration @EnableAutoConfiguration扫描配置类开启自动装配   @ComponentScan 注解）
@SpringCloudApplication  //等同于上面的三个 进行了封装
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
