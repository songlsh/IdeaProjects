package com.sls.jdk8.functionInterface;

public class Test {

    // 直接调用该接口的一个方法
    public void doworks(ITestWork work) {
        work.dowork();
        System.out.println("do some work");
    }


    public static void main(String[] args) {
      /*
      //1、 Test test = new Test();

      // 2、可以利用lambda表达是来表示该接口类函数接口的一个实现
       ITestWork work1 = ()->{
           System.out.println("dowork is 实现了");
       };
       //3、调用方法
       test.doworks(work1);*/

        // 把上面的步骤合起来就是
        new Test().doworks(()->{
            System.out.println("dowork is 实现了");
        });
    }
}
