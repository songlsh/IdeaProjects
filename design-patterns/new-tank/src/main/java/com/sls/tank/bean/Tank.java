package com.sls.tank.bean;

import com.sls.tank.Direction;
import com.sls.tank.Group;
import com.sls.tank.LoadImage;
import com.sls.tank.TankFrameExtends;
import com.sls.tank.strategy.DefaultFireImpl;
import com.sls.tank.strategy.FourFireImpl;
import com.sls.tank.strategy.IFire;

import java.awt.*;
import java.util.*;

/**
 * 构建tank类
 */
public class Tank {
    // 坦克 坐标x
    private int x;
    // 坦克 坐标y
    private int y ;
    // 坦克 宽
    public int TANK_WIDTH = LoadImage.tankD.getWidth();
    // 坦克高
    public int TANK_HEIGHT = LoadImage.tankD.getWidth();

    // 定义一个方向
    public Direction dir = Direction.LEFT;
    // 偏移量
    private static final int MOVE = 2;

    //添加画板对象来获取对应的子弹类

    private GameModel gm = null;
    public Group group = Group.BAD;

    //坦克的停止和移动

    private  boolean moving = true;
    private boolean living = true;

    private IFire fire;
    // 减少碰撞检测中每次都进行创建这个对象，可以把这个和每个tank位置进行绑定
    public Rectangle rectangle = new Rectangle();

    public GameModel getGm() {
        return gm;
    }

    public Tank(int x, int y, Direction dir, GameModel gm, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;
        // 坦克策略模式
        fire = this.group == Group.BAD ? new DefaultFireImpl(): new FourFireImpl();

        // 初始化rectangle对象和tank位置进行绑定
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.height = this.TANK_HEIGHT;
        rectangle.width = this.TANK_WIDTH;

    }

    public boolean isMoving() {
        return moving;
    }
    public void setMoving(boolean moving) {
        this.moving = moving;

    }
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    // tank的移动方法
    public void paintMove(Graphics g){
        // 画出黑方块  x 轴   y轴    宽         高
//        g.fillRect(x, y, TANK_WIDTH,TANK_HEIGHT);
        if (!living) return;
        drawTank(g);
        move();
    }
    // 模拟移动 实际就是让坐标发生了变化
    private void move() {
        if(! moving) return;
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
        // tank 随机移动
        if (this.group == Group.BAD && new Random().nextInt(100) > 95) {
            this.fire();
        }
        // tank 随机朝向
        if (this.group == Group.BAD && new Random().nextInt(100) > 95) {
            randmonDir();
        }
        // tank的边界位置
        borderCheck();
        // tank移动完以后需要 更新可能的碰撞坐标
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    private void borderCheck() {
        if(this.x < 2) x = 2;
        if(this.y < 20) y = 20;
        if(this.x > TankFrameExtends.PAINT_WIDTH)this.x = TankFrameExtends.HEIGHT - TANK_WIDTH-2;
        if(this.y > TankFrameExtends.PAINT_WIDTH-TANK_HEIGHT) this.y = TankFrameExtends.HEIGHT-TANK_HEIGHT-2;

    }

    private void randmonDir() {
        int anInt = new Random().nextInt(4);
        this.dir = Direction.values()[anInt];
    }

    private void drawTank(Graphics g) {
        switch (dir) {
            case LEFT:
                g.drawImage(LoadImage.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(LoadImage.tankR,x,y,null);
                break;
            case UP:
                g.drawImage(LoadImage.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(LoadImage.tankD,x,y,null);
                break;
            default:
                break;
        }
    }

    public void fire() {
        //fire.fire(this);

        int bulletX = this.getX() + this.TANK_WIDTH/2 - LoadImage.BulletD.getWidth()/2;
        int bulletY = this.getY() + this.TANK_HEIGHT/2 - LoadImage.BulletD.getHeight()/2;
        new Bullet(bulletX, bulletY, this.dir, this.getGm(), this.group);
    }

    public void die() {
        this.living = false;
        gm.getEnemyTanks().remove(this);
    }
}
