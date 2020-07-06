package com.sls.ribbon.controller;

import com.sls.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private HelloService helloService;


    @GetMapping("/ribbon-comsumer")
    public String helloConsumer(){

        return  helloService.serviceHello();
    }
}
