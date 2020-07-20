package com.sls.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TestList {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        // 使用这种循环也可以但是注意下标位置
        for (int i = 0; i < 10; i++) {
             System.out.println(list);
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).equals("2")) {
                    list.remove("2");
                }
            }
             System.out.println(list);
            list.add(new Random().nextInt(3)+"");
        }

        /**
         * foreach的遍历使用的是Itertor的遍历方式，会引起ConcurrentModificationException();
         */
//        for (String s: list) {
//            if (s.equals("2")) {
//                list.remove("2");
//            }
//            list.add("4");
//        }
        //IterDel(list);


    }

    /**
     * 使用Iteror 自己的遍历方式进行遍历， 删除操作没有影响
     * @param list
     */
    private static void IterDel(ArrayList<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 2) {
                iterator.remove();
            }
        }
    }
}
