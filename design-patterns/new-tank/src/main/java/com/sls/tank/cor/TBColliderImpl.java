package com.sls.tank.cor;

import com.sls.tank.LoadImage;
import com.sls.tank.bean.Bullet;
import com.sls.tank.bean.Exploded;
import com.sls.tank.bean.GameObject;
import com.sls.tank.bean.Tank;

public class TBColliderImpl implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Bullet) {
            Tank tank = (Tank)o1;
            Bullet bullet = (Bullet)o2;
            return approach(tank, bullet);
        } else if (o2 instanceof Tank && o1 instanceof Bullet) {
            Tank tank = (Tank)o2;
            Bullet bullet = (Bullet)o1;
           return approach(tank, bullet);
        }else{
            return false;
        }
    }

    /**
     * 坦克和子弹相遇  false证明没坏 true 证明撞坏了
     * @param tank
     * @param bullet
     * @return
     */
    private boolean approach(Tank tank, Bullet bullet) {
        if (bullet.getGroup() == tank.getGroup()) return false;
        if (tank.rectangle.intersects(bullet.getRectangle())) {
            bullet.die();
            tank.die();
            int exploedX = tank.getX() + LoadImage.tankD.getWidth() / 2;
            int exploedY = tank.getY() + LoadImage.tankD.getHeight() / 2;
            bullet.getGm().getGameObjects().add(new Exploded(exploedX, exploedY, bullet.getGm()));
            return true;
        }
        return false;
    }

    public TBColliderImpl() {
    }
}
