package com.sls.tank.cor;

import com.sls.tank.LoadImage;
import com.sls.tank.bean.Bullet;
import com.sls.tank.bean.Exploded;
import com.sls.tank.bean.GameObject;
import com.sls.tank.bean.Tank;
import com.sls.tank.bean.Wall;

public class WBColliderImpl implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Wall && o2 instanceof Bullet) {
            Wall wall = (Wall)o1;
            Bullet bullet = (Bullet)o2;
            return approach(wall, bullet);
        } else if (o2 instanceof Wall && o1 instanceof Bullet) {
            Wall tank = (Wall)o2;
            Bullet bullet = (Bullet)o1;
           return approach(tank, bullet);
        }else{
            return false;
        }
    }

    /**
     * 坦克和子弹相遇  false证明没坏 true 证明撞坏了
     * @param wall
     * @param bullet
     * @return
     */
    private boolean approach(Wall wall, Bullet bullet) {
        if (wall.getRectangle().intersects(bullet.getRectangle())) {
            bullet.die();
//            wall.die();
//            int exploedX = wall.getX() + LoadImage.BulletD.getWidth() / 2;
//            int exploedY = wall.getY() + LoadImage.BulletD.getHeight() / 2;
//            bullet.getGm().getGameObjects().add(new Exploded(exploedX, exploedY, bullet.getGm()));
            return true;
        }
        return false;
    }

    public WBColliderImpl() {
    }
}
