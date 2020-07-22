package com.sls.tank.cor;

import com.sls.tank.bean.GameObject;

public interface Collider {

    // true 证明相撞毁掉了 false证明没事
    boolean collide(GameObject o1,GameObject o2);
}
