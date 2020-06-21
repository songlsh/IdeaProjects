package com.sls.jdk8.stream.test;

import com.sls.jdk8.stream.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestListStream {
    private  static  List<User> list = new ArrayList<>(5);
    static {
        list.add(new User("lisi",5));
        list.add(new User("wangwu",6));
        list.add(new User("zhaosi",7));
    }

    public static void main(String[] args) {
        User usr = new User("lisi",66);
        List<String> collect = list.stream().filter(user -> !user.getName().equals(usr.getName())).map(user ->
              user.getName()).collect(Collectors.toList());
        System.out.println(collect);
}
}
