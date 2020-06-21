package com.sls.jdk8.functionInterface;

/**
 * 接口类函数的使用
 *
 *
 */
@FunctionalInterface
public interface ITestWork {

    void dowork();

//
    public static void doSleep(){

    }

    public default void doB(){

    }
//
//    // java object 中的方法
    public String toString();

    public boolean equals(Object obj);

}
