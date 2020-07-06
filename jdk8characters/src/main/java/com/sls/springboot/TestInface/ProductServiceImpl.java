package com.sls.springboot.TestInface;

// 没有添加注解
public class ProductServiceImpl implements ProductService {

    @Override
    public void doSomething() {
        System.out.println("domestic hello world");
    }
}
