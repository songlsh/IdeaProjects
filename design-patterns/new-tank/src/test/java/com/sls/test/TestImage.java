package com.sls.test;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TestImage {

    @Test
    public void test(){
        BufferedImage imageIO = null;
        try {
            // 加载图片
            imageIO= ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/BadTank2.png"));
             Assert.assertNotNull(imageIO);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
