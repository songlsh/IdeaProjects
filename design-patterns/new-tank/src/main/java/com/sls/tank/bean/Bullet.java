package com.sls.tank.bean;

import com.sls.tank.Direction;
import com.sls.tank.Group;
import com.sls.tank.LoadImage;
import com.sls.tank.TankFrameExtends;

import java.awt.*;

/**
 * 构建tank类
 */
public class Bullet  extends  GameObject{

    private static int WIDTH = LoadImage.BulletD.getWidth();

    private static int HEIGHT = LoadImage.BulletD.getHeight();

    // 定义一个方向
    private Direction dir = Direction.LEFT;
    // 偏移量
    private static final int MOVE = 3;

    // 子弹是否存活
    private boolean living = true;

    //子弹区分
    private Group group = Group.BAD;

    // 减少碰撞检测中每次都进行创建这个对象，可以把这个和每个bullet位置进行绑定
    private Rectangle rectangle = new Rectangle();

    public Bullet(int x, int y, Direction dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

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

    public Group getGroup() {
        return group;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    // tank的移动方法
    public void paintMove(Graphics g){

        if(!living) GameModel.getInstace().getGameObjects().remove(this);;
        drawImage(g);
        move();
    }
    // 画出子弹的图片
    private void drawImage(Graphics g) {
        switch (dir) {
            case LEFT:
                g.drawImage(LoadImage.BulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(LoadImage.BulletR,x,y,null);
                break;
            case UP:
                g.drawImage(LoadImage.BulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(LoadImage.BulletD,x,y,null);
                break;
            default:
                break;
        }
    }
    // 子弹的移动轨迹 按照按键的方向来确定
    private void move() {
        switch (dir) {
            case LEFT:
                x -= MOVE;
                break;
            case RIGHT:
                x += MOVE;
                break;
            case UP:
                y -= MOVE;
                break;
            case DOWN:
                y += MOVE;
                break;
            default:
                break;
        }
        // tank移动完以后需要 更新可能的碰撞坐标
        rectangle.x = this.x;
        rectangle.y = this.y;

        //子弹移动完之后判断是否超出了边界
        if (x < 0 || y < 0 || x > TankFrameExtends.getPaintWidth() || y > TankFrameExtends.getPaintHeight()) {
            living = false;
        }
    }

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()) return;

        // 判断 子弹和坦克相交事件
//               Rectangle rectangle1 = new Rectangle(this.x, this.y, LoadImage.BulletD.getWidth(),LoadImage.BulletD.getHeight());
//
//        Rectangle rectangle2 = new Rectangle(tank.getX(),tank.getY(),LoadImage.tankD.getWidth(),LoadImage.tankD.getHeight());

        if (tank.rectangle.intersects(this.rectangle)) {
            this.die();
            tank.die();
            int exploedX = tank.getX()+LoadImage.tankD.getWidth()/2;
            int exploedY = tank.getY()+LoadImage.tankD.getHeight()/2;
            GameModel.getInstace().getGameObjects().add(new Exploded(exploedX,exploedY));
        }
    }

    public void die() {
        this.living = false;
    }
}
