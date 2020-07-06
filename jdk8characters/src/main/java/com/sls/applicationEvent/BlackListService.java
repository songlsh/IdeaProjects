package com.sls.applicationEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


public class BlackListService implements ApplicationEventPublisherAware {

    private  List<String> list;


    private ApplicationEventPublisher publisher;

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void sendEmail(String address, String content){
        if(list.contains(address)){
            //发布event
            publisher.publishEvent(new BlackListEvent(this,address,content));
            return;
        }
        System.out.println("邮件已发送");
    }


}
