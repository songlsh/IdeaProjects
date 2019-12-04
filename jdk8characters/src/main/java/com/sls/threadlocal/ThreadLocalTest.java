package com.sls.threadlocal;

public class ThreadLocalTest {

    public static void main(String[] args) {
        String name = Thread.currentThread().getName();
        System.out.println("Thead-name: "+name);
        ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();
        ThreadLocal<String> StringThreadLocal = new ThreadLocal<>();
        ThreadLocal<Double> doubleThreadLocal = new ThreadLocal<>();

        integerThreadLocal.set(1);
        StringThreadLocal.set("main");
        doubleThreadLocal.set(1.2);

        System.out.println(name+" "+integerThreadLocal.get());
        System.out.println(name+" "+StringThreadLocal.get());
        System.out.println(name+" "+doubleThreadLocal.get());
        new Thread(()->{
            String name1 = Thread.currentThread().getName();
            System.out.println("Thead-name: "+name1);
            integerThreadLocal.set(2);
            StringThreadLocal.set(name1);
            doubleThreadLocal.set(2.2);

            System.out.println(name1+" "+integerThreadLocal.get());
            System.out.println(name1+" "+StringThreadLocal.get());
            System.out.println(name1+" "+doubleThreadLocal.get());

        }).start();

        new Thread(()->{
            System.out.println(" "+integerThreadLocal.get());
            System.out.println(" "+StringThreadLocal.get());
            System.out.println(" "+doubleThreadLocal.get());

        }).start();

    }
}
