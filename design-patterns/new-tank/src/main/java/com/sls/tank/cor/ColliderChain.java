package com.sls.tank.cor;

import com.sls.tank.bean.GameObject;
import com.sls.tank.prop.ReadProSingleton;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider {

    private static List<Collider> colliders = new LinkedList<>();

    static {
        ReadProSingleton instance = ReadProSingleton.getInstance();
        String colliderChains = (String) instance.getPro("colliderChains");
        String[] chain = colliderChains.split(",");
        for (int i = 0; i < chain.length; i++) {
            Collider o = null;
            try {
                o = (Collider) Class.forName(chain[i]).getDeclaredConstructor().newInstance();
                add(o);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public ColliderChain() {
    }
    private static void add(Collider bbCollider) {
        colliders.add(bbCollider);
    }


    public boolean collide(GameObject gameObject, GameObject gameObject1) {
        for (int i = 0; i < colliders.size(); i++) {
            if (colliders.get(i).collide(gameObject1, gameObject)) {
                return false;
            }
        }
        return true;
    }
}
