package com.sls;

import com.sls.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestApplicationTest {

    @Autowired
    private Book book;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test(){
        System.out.println(book.getDesc());
        String s = jdbcTemplate.queryForObject("select count(1) from user", String.class);
        System.out.println(s);
    }

}