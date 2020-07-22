package com.sls.tank.bean;

import com.sls.tank.Direction;
import com.sls.tank.Group;
import com.sls.tank.cor.ColliderChain;
import com.sls.tank.prop.ReadProSingleton;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * model
 */
public class GameModel  {

    //单例化GameModel
    private static  final  GameModel gameModel = new GameModel();

    static {
        gameModel.init();
    }

    private GameModel() {
    }
    // 设定我方坦克为好
    Tank tank;
    private  List<GameObject> gameObjects = new ArrayList<>();

    ColliderChain compiler = new ColliderChain();


    public static GameModel getInstace() {
        return gameModel;
    }

    private void init() {
        tank = new Tank(200, 400, Direction.UP, Group.GOOD);
        ReadProSingleton instance = ReadProSingleton.getInstance();
        int tankTest = Integer.parseInt((String) instance.getPro("test"));

        for (int i = 0; i < tankTest; i++) {
            new Tank(80+i*80, 100, Direction.DOWN,  Group.BAD);
        }
        new Wall(100,300, Direction.LEFT);
        new Wall(300,200, Direction.RIGHT);
        new Wall(500,300, Direction.UP);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.BLACK);
//        g.drawString("子弹：" + gameObjects.size(), 10, 50);
//        g.drawString("敌方：" + gameObjects.size(), 10, 70);
        g.setColor(c);
        // 我方tank的画法  【tank或子弹 的移动实际上就是位置坐标发生了变化】
        tank.paintMove(g);

        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paintMove(g);
        }
        // 设定敌方的坦克和所有子弹的碰撞情况
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = 0; j < gameObjects.size(); j++) {
                GameObject gameObject = gameObjects.get(i);
                GameObject gameObject1 = gameObjects.get(j);

                compiler.collide(gameObject,gameObject1);
            }
        }
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public Tank getMainTank() {
        return tank;
    }
}
