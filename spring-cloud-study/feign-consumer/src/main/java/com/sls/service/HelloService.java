package com.sls.service;

import com.sls.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient("rookie")
public interface HelloService {

    @RequestMapping("/hello")
    String hello();

    @GetMapping("/hello1")
    String test1(@RequestParam("name") String name);

    @GetMapping("/hello2")
    User test2(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @PostMapping("/hello3")
    String  test3(@RequestBody User user);
}
