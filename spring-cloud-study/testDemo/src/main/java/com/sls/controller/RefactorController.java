package com.sls.controller;

import com.sls.DTO.User;
import com.sls.service.HelloServiceReactor;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RefactorController implements HelloServiceReactor {
    @Override
    public String hello() {
        return  "hello";
    }

    @Override
    public String test1(String name) {
        return "hello"+ name;
    }

    @Override
    public User test2(String name, Integer age) {
        return new User(name, age);
    }

    @Override
    public String test3(User user) {
        return "Hello"+user.getName()+user.getAge();
    }
}
