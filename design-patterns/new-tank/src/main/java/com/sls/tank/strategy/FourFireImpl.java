package com.sls.tank.strategy;

import com.sls.tank.Direction;
import com.sls.tank.LoadImage;
import com.sls.tank.bean.Bullet;
import com.sls.tank.bean.Tank;

public class FourFireImpl implements IFire {
    @Override
    public void fire(Tank tank) {
        int bulletX = tank.getX() + tank.TANK_WIDTH/2 - LoadImage.BulletD.getWidth()/2;
        int bulletY = tank.getY() + tank.TANK_HEIGHT/2 - LoadImage.BulletD.getHeight()/2;
        //循环添加四个方向
        for (Direction dir:Direction.values()) {
             new Bullet(bulletX, bulletY, dir, tank.getGm(), tank.group);
        }
    }
}
