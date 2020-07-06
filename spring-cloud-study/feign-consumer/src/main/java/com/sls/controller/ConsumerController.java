package com.sls.controller;

import com.sls.bean.User;
import com.sls.service.HelloService;
import com.sls.service.HelloServiceReactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

//    @Autowired
//    HelloService helloService;

    @Autowired
    HelloServiceReactor helloServiceReactor;


//    @GetMapping(value = "/feign-consumer")
//    public String helloConsumer(){
//        return helloService.hello();
//    }
//    @GetMapping(value = "/feign-consumer1")
//    public String helloConsumer1(){
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(helloService.test1("userName")).append("  ");
//        stringBuilder.append(helloService.test2("lisi",15)).append("  ");
//        stringBuilder.append(helloService.test3(new User("lala",22))).append("   ");
//        return stringBuilder.toString();
//    }

    @GetMapping(value = "/feign-consumer2")
    public String helloConsumer2(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(helloServiceReactor.test1("userName")).append("  ");
        stringBuilder.append(helloServiceReactor.test2("lisi",15)).append("  ");
        stringBuilder.append(helloServiceReactor.test3(new com.sls.DTO.User("lala",22))).append("   ");
        return stringBuilder.toString();
    }

}
