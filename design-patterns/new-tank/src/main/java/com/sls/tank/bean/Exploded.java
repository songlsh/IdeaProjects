package com.sls.tank.bean;

import com.sls.tank.Direction;
import com.sls.tank.Group;
import com.sls.tank.LoadImage;
import com.sls.tank.TankFrameExtends;

import java.awt.*;
import java.util.Random;

/**
 * 构建炸弹类
 */
public class Exploded {
    // 炸弹 坐标x
    private int x;
    // 炸弹 坐标y
    private int y ;

    private GameModel gm;
    private boolean living = true;

    private  int step = 0;
    public Exploded(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm =gm;
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

    // tank的移动方法
    public void paintMove(Graphics g){
        // 画出黑方块  x 轴   y轴    宽         高
//        g.fillRect(x, y, TANK_WIDTH,TANK_HEIGHT);
        drawTankExploded(g);
    }
    private void drawTankExploded(Graphics g) {
     g.drawImage(LoadImage.exploded[step++], x, y, null);
     if(step >= LoadImage.exploded.length)
     gm.getExploded().remove(this);
    }
}
