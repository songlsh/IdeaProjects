package com.sls.tank.prop;

import java.io.IOException;
import java.util.Properties;

public class ReadProSingleton {

    private static final ReadProSingleton readProSingleton = new ReadProSingleton();

    private ReadProSingleton() {

    }

    public static ReadProSingleton getInstance() {
        return readProSingleton;
    }

    static Properties properties = new Properties();

    static {
        try {
            properties.load(ReadProSingleton.class.getClassLoader().getResourceAsStream("class.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  Object getPro(String key){
        if(properties == null) return null;
        Object o = properties.get(key);

        return o;
    }

}
