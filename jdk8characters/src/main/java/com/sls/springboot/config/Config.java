package com.sls.springboot.config;

import com.sls.springboot.testImport.TestBeanDefinitionRegister;
import com.sls.springboot.testImport.TestSelect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TestSelect.class, TestBeanDefinitionRegister.class})  //导入 ImportSelector 类 ,BeanDefinitionRegister.class
public class Config {
}
