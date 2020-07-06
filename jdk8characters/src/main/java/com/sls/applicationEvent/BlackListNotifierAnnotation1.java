package com.sls.applicationEvent;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class BlackListNotifierAnnotation1 {

    @EventListener   //利用注解进行的监听 event 发生
    @Order(1)
    public void Event(BlackListEvent event){

        System.out.println(event.getAddress()+"不发邮件");
    }
}
