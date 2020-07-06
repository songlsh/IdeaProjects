package com.sls.gateway;


import com.sls.gateway.filter.AccessFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@EnableZuulProxy
@SpringCloudApplication
public class GateApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GateApplication.class).run(args);
    }

    public AccessFilter getFilter(){
      return   new AccessFilter();
    }
}
