package com.sls.poolThread;

import ch.qos.logback.core.net.SyslogOutputStream;

public class Test {

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(10,3);

        for (int i = 0; i < 5; i++) {
            pool.submit(()->{
                System.out.println("放入Thread");
            });
        }
        pool.shutDown();
    }
}
