package com.sls.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 加载静态的图片内容
 */
public class LoadImage {
    public static  BufferedImage tankU ;
    public static  BufferedImage tankD ;
    public static  BufferedImage tankL ;
    public static  BufferedImage tankR ;

    public static  BufferedImage BulletU ;
    public static  BufferedImage BulletD ;
    public static  BufferedImage BulletL ;
    public static  BufferedImage BulletR ;

    public static  BufferedImage exploded[] = new BufferedImage[16];

    static {
        try {
            tankU = ImageIO.read(LoadImage.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankL = ImageIO.read(LoadImage.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankR= ImageIO.read(LoadImage.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankD = ImageIO.read(LoadImage.class.getClassLoader().getResourceAsStream("images/tankD.gif"));



            BulletU = ImageIO.read(LoadImage.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            BulletD = ImageIO.read(LoadImage.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            BulletL= ImageIO.read(LoadImage.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            BulletR = ImageIO.read(LoadImage.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));

            for (int i = 1; i < exploded.length; i++) {
                exploded[i] = ImageIO.read(LoadImage.class.getClassLoader().getResourceAsStream("images/e"+i+".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
