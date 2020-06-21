package com.sls.jdk8.treeMap;

import com.sls.bean.Person;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class TreeMapTest {
    private volatile static TreeMap<String, Person> treeMap = new TreeMapModel<String, Person>();
    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap();
        map.put("2018-07-11", "ccccc");
        map.put("2018-17-26", "aaaaa");
        map.put("2018-06-11", "bbbbb");
        map.put("2018-06-28", "ddddd");
        map.put("2017-09-29", "ccccc");
        map.put("2017-04-26", "aaaaa");
        map.put("2017-10-31", "bbbbb");
        map.put("2017-01-01", "ddddd");
        TreeMap<String,HashMap<String,String>> treeMaps = new TreeMap();
        treeMaps.put("1",map);
        System.out.println(treeMaps);

        for (String key : treeMaps.keySet()) {
            HashMap hashMap = treeMaps.get(key);
            Iterator iterator = hashMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String,String> next = (Map.Entry<String, String>) iterator.next();
                if("2018-07-11".equals(next.getKey())){
                    iterator.remove();
                }
            }
        }
        System.out.println(treeMaps);
    }

    public static void test(){
        List<Map.Entry<String, Person>> list = Collections.synchronizedList(new ArrayList<Map.Entry<String, Person>>(treeMap.entrySet()));
        Collections.sort(list,(Map.Entry<String, Person> o1, Map.Entry<String, Person> o2)->{
            return o1.getValue().compareTo(o2.getValue());
        });
        Iterator<Map.Entry<String, Person>> iterator1 = list.iterator();
//按key降序排序
        Map<String, String> map = new TreeMap<>(
//                new Comparator<String>() {
////                    public int compare(String obj1, String obj2) {
////                        return obj2.compareTo(obj1);
////                    }
////                }
        );

        map.put("2018-07-11", "ccccc");
        map.put("2018-17-26", "aaaaa");
        map.put("2018-06-11", "bbbbb");
        map.put("2018-06-28", "ddddd");
        map.put("2017-09-29", "ccccc");
        map.put("2017-04-26", "aaaaa");
        map.put("2017-10-31", "bbbbb");
        map.put("2017-01-01", "ddddd");

        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String k = (String) iterator.next();
            System.out.println(k + "--" + map.get(k));
        }
    }
}
