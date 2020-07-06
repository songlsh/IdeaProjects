package com.sls.springboot.config;


import com.sls.App;
import com.sls.bean.Person;
import com.sls.springboot.TestInface.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {App.class})
public class AppConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ProductService productService;
    @Test
    public void testSelector(){

//        AppConfig bean = applicationContext.getBean(AppConfig.class);
//        System.out.println(bean.getPerson());

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames){
            System.out.println(name);
        }

    }
    @Test
    public void testRegistor(){
        this.productService.doSomething();
    }
}