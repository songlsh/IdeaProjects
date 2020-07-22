package com.sls.tank.bean;

import com.sls.tank.Direction;
import com.sls.tank.Group;
import com.sls.tank.LoadImage;
import com.sls.tank.TankFrameExtends;

import java.awt.*;

/**
 * 构建wall类
 */
public class Wall extends  GameObject{

    private static int WIDTH = 300;

    private static int HEIGHT = 30;

    // 定义一个方向
    private Direction dir = Direction.LEFT;

    // 墙体是否存活
//    private boolean living = true;

    // 减少碰撞检测中每次都进行创建这个对象，可以把这个和每个bullet位置进行绑定
    private Rectangle rectangle = new Rectangle();

    public Wall(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;

        // 初始化rectangle对象和子弹位置进行绑定
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.height = HEIGHT;
        rectangle.width =  WIDTH;

        GameModel.getInstace().getGameObjects().add(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    // wall的移动方法
    public void paintMove(Graphics g){

        drawImage(g);
    }
    // 画出墙的图片
    private void drawImage(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.black);
        g.fillRect(x,y,WIDTH,HEIGHT);
        g.setColor(color);
    }

}
