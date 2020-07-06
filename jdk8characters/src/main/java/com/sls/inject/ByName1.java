package com.sls.inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("byType")
public class ByName1 implements Name {

    @Value("${person.age}")
    private int age;

    @Value("${person.age}")
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Override
    public void getSome() {
        System.out.println(getName());
    }
}
