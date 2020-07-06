package com.sls.controller;

//import com.netflix.discovery.DiscoveryClient;

import com.sls.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    private static final Logger LOGGER= LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient client;

    @GetMapping("/hello")
    public String test(){
//        client.getNextServerFromEureka(DiscoveryClient.HTTP_X_DISCOVERY_ALLOW_REDIRECT, false);
        List<String> services = client.getServices();
        List<ServiceInstance> rookie = client.getInstances("rookie");
        for(String service:services){

        LOGGER.info(service);
        }

        for(ServiceInstance service:rookie){

        LOGGER.info(service.getHost()+"port: "+service.getPort());
        }
        return "hello world";
    }

    @GetMapping("/hello1")
    public String test1(@RequestParam String name){
        return "Hello"+name;
    }

    @GetMapping("/hello2")
    public User test2(@RequestHeader String name, @RequestHeader Integer age){
        return new User(name,age);
    }

    @PostMapping("/hello3")
    public String  test3(@RequestBody User user){
        return user.toString();
    }
}
