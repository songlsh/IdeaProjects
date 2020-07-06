package com.sls.testStarter;

import com.sls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TestStarter {

    @Autowired
    private UserService userService;

    public void test(){
        System.out.println(userService.toString());
    }

}
