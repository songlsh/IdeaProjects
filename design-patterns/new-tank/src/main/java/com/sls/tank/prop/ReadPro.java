package com.sls.tank.prop;

import java.io.IOException;
import java.util.Properties;

public class ReadPro {

    static Properties properties = new Properties();

    static {
        try {
            properties.load(ReadPro.class.getClassLoader().getResourceAsStream("class.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getPro(String key){
        if(properties == null) return null;
        Object o = properties.get(key);

        return o;
    }

}
