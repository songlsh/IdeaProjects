package com.sls.javaBean;

import ch.qos.logback.core.net.SyslogOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix = "person")
public class JavaBean {

//    @Value("${age}")
    private int age;

    private String name;

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void test(){
        System.out.println(age);
        System.out.println(name);
    }
}
