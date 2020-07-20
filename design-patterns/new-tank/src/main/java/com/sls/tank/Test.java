package com.sls.tank;

import com.sls.tank.bean.Tank;
import com.sls.tank.prop.ReadPro;
import com.sls.tank.prop.ReadProSingleton;

public class Test {

    public static void main(String[] args) {
        TankFrameExtends tankFrameExtends = new TankFrameExtends();
        /**
         * Integer.valueof() 范围-128 to 127,
         * Integer.parseInt()范围,
         */
        while (true) {
            try {
                Thread.sleep(25);
                tankFrameExtends.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
