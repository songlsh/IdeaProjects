package com.sls.tank.cor;

import com.sls.tank.bean.GameObject;
import com.sls.tank.bean.Tank;

public class TTColliderImpl implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank tank1 = (Tank)o1;
            Tank tank2 = (Tank)o2;
            if (tank1.rectangle.intersects(tank2.rectangle)) {
//                tank1.back();
//                tank2.back();
//                System.out.println(111);
            }
        }
        return false;
    }

    public TTColliderImpl() {
    }
}
