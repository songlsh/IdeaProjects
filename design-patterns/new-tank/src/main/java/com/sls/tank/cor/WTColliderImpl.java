package com.sls.tank.cor;

import com.sls.tank.bean.Bullet;
import com.sls.tank.bean.Exploded;
import com.sls.tank.bean.GameObject;
import com.sls.tank.bean.Tank;
import com.sls.tank.bean.Wall;

public class WTColliderImpl implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Wall && o2 instanceof Tank) {
            Wall wall = (Wall)o1;
            Tank tank = (Tank)o2;
            return approach(wall, tank);
        } else if (o2 instanceof Wall && o1 instanceof Tank) {
            Wall wall = (Wall)o2;
            Tank tank = (Tank)o1;
           return approach(wall, tank);
        }else{
            return false;
        }
    }

    /**
     * 坦克和子弹相遇  false证明没坏 true 证明撞坏了
     * @param wall
     * @param tank
     * @return
     */
    private boolean approach(Wall wall, Tank tank) {
        if (wall.getRectangle().intersects(tank.rectangle)) {
            tank.back();
            return true;
        }
        return false;
    }

    public WTColliderImpl() {
    }
}
