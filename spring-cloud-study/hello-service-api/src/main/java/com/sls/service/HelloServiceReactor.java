package com.sls.service;

import com.sls.DTO.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/refactor")
public interface HelloServiceReactor {

    @RequestMapping("/hello4")
    String hello();

    @GetMapping("/hello5")
    String test1(@RequestParam("name") String name);

    @GetMapping("/hello6")
    User test2(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @PostMapping("/hello7")
    String  test3(@RequestBody User user);
}
