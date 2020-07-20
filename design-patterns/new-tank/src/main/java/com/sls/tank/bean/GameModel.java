package com.sls.tank.bean;

import com.sls.tank.Direction;
import com.sls.tank.Group;
import com.sls.tank.prop.ReadProSingleton;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 相当于把业务进行抽取出来
 */
public class GameModel {

    //子弹数量
    private List<Bullet> bullets = new ArrayList<Bullet>();

    //敌方坦克
    private List<Tank> enemyTanks = new ArrayList<>();

    // 设定我方坦克为好
    Tank tank = new Tank(200, 400, Direction.UP, this, Group.GOOD);

    //爆炸情况
    private List<Exploded> exploded = new ArrayList<>();


    public GameModel() {
        // 初始化敌方坦克
        ReadProSingleton instance = ReadProSingleton.getInstance();
        int tankTest = Integer.parseInt((String) instance.getPro("test"));

        for (int i = 0; i < tankTest; i++) {
            this.getEnemyTanks().add(new Tank(50+i*80, 200, Direction.DOWN, this, Group.BAD));
        }
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public List<Tank> getEnemyTanks() {
        return enemyTanks;
    }

    public void setEnemyTanks(List<Tank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public List<Exploded> getExploded() {
        return exploded;
    }

    public void setExploded(List<Exploded> exploded) {
        this.exploded = exploded;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.drawString("子弹：" + bullets.size(), 10, 50);
        g.drawString("敌方：" + enemyTanks.size(), 10, 70);
        g.setColor(c);
        // 我方tank的画法  【tank或子弹 的移动实际上就是位置坐标发生了变化】
        tank.paintMove(g);
        // 我方子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paintMove(g);
        }

        // 敌方坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            enemyTanks.get(i).paintMove(g);
        }
        for (int i = 0; i < exploded.size(); i++) {
            exploded.get(i).paintMove(g);
        }
        // 设定敌方的坦克和所有子弹的碰撞情况
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemyTanks.size(); j++) {
                bullets.get(i).collideWith(enemyTanks.get(j));
            }
        }
    }

    public Tank createMyTank() {
        return tank;
    }
}
