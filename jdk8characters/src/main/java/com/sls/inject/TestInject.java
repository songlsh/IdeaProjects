package com.sls.inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestInject {


//    =================================

//    private Name name;
//=========================================

    @Resource(name="byType")
    private Name name;


    /**
     *  @Autowired 默认按照类型注入  和 @Qualifier 联合使用相当于byName注入
     *  //    @Autowired
     * //    @Qualifier("byType")
     *
     *
     */
    @Test
    public void testByAuto(){
        name.getSome();
    }


    /**
     *
     * //    @Inject
     * //    @Named("byType")
     *  @Inject 默认按照类型注入  和 @named联合使用相当于byName注入
     */
    @Test
    public void testByInjecy(){
        name.getSome();
    }

    /**
     * @Resource(name="byType")
     *
     *  @Resource 默认按照类型注入
     */
    @Test
    public void testByResource(){
        name.getSome();
    }


}
