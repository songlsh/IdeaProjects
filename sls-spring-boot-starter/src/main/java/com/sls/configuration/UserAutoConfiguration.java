package com.sls.configuration;

import com.sls.service.UserService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(UserProperties.class)
public class UserAutoConfiguration {

    @Bean
    public UserService getBean(UserProperties userProperties) {
        //创建组件实例
        UserService userService = new UserService();
        userService.setUsername(userProperties.getUsername());
        userService.setPassword(userProperties.getPassword());
        return userService;
    }
}