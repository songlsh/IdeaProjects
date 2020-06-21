package com.sls.jdk8.stream.test;

import com.sls.jdk8.stream.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestForEach {
    private  static  List<User> list = new ArrayList<>();
    static {
        List<User> list1 = Arrays.asList(
                // name，age
                new User("张三", 11),
                new User("王五", 20),
                new User("王五", 91),
                new User("张三", 8),
                new User("李四", 45),
                new User("李四", 44),
                new User("李四", 44)
        );
        list.addAll(list1);
    }

    @Test
    public  void testForEach() {
        list.forEach(x->{
            System.out.println("lambda=>"+x);
        });
        System.out.println("++++++++++++++++");
        list.forEach(System.out::println);
        System.out.println("++++++java8 stream++++++++++");
//        list.stream().forEach(System.out::print);

        list.stream().forEach(x->{
            System.out.println(x);
        });
    }
    @Test
    public void testSort(){

        System.out.println("---排序前--");
        list.forEach(x-> System.out.println(x));
        System.out.println("---排序后--");
        Collections.sort(list,(x,y)->{return x.getAge().compareTo(y.getAge());

//                new Comparator<User>() {
//            @Override
//            public int compare(User o1, User o2) {
//                return o1.getAge().compareTo(o2.getAge());
//            }
        });
        list.forEach(System.out::println);
        System.out.println("----排序stream后----");
        list.stream().sorted(Comparator.comparing(User::getAge)).forEach(System.out::println);
    }

    @Test
    public void testFilter(){
        //输出年龄大于50岁的人
        System.out.println("---过滤前---");
        list.forEach(System.out::println);
        System.out.println("---过滤后--");
        list.forEach(x->{
            if(x.getAge()>50)
                System.out.println(x);
        });
        System.out.println("-- stream过滤后---");
        list.stream().filter((User user)->user.getAge()>50).forEach(System.out::println);
    }

    @Test
    public void testLimit(){

        // 从第三个开始截断，只输出前三个
        System.out.println("-----截断前-----");
        list.forEach(user -> System.out.println(user));
        System.out.println("-----截断后-----");
        // java 8 前
        System.out.println("java 8 前");
        for (int i = 0; i < 3; i++) {
            System.out.println(list.get(i));
        }
        // java 8 stream
        System.out.println("java 8 stream");
        list.stream().limit(3).forEach(System.out::print);
    }

    @Test
    public void testSkip(){

        // 跳过前三个元素，从第四个开始输出
        System.out.println("-----截断前-----");
        list.forEach(user -> System.out.println(user));
        System.out.println("-----截断后-----");
        // java 8 前
        System.out.println("java 8 前");
        for (int i = 3; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        // java 8 stream
        System.out.println("java 8 stream");
        list.stream().skip(3).forEach(System.out::print);
    }

    @Test
    public void testDistinct(){

        //去掉list里面重复的数据
        System.out.println("-----去重前-----");
        list.forEach(user -> System.out.println(user));
        System.out.println("-----去重后-----");
        // java 8 前
        System.out.println("java 8 前");
//        for (int i = 0; i < list.size()-1; i++) {
//            for(int j=list.size()-1;j>i;j--){
//              if(list.get(i).getAge().equals(list.get(j).getAge())&&list.get(i).getName().equals(list.get(j).getName())){
//                  list.remove(i);
//              }
//            }
//        }
//        list.forEach(user -> System.out.println(user));
        // java 8 stream
        System.out.println("java 8 stream");
        list.stream().distinct().forEach(System.out::println);
    }

    /**
     * 去重+按照年龄大于40以后从小到大+只取前二
     * distinct  filter  sort  limit  skip
     */
    @Test
    public void demo(){
        System.out.println("-----去重前-----");
        list.forEach(user -> System.out.println(user));
        System.out.println("-----去重后-----");
        list.stream().distinct().filter((User user)->user.getAge()>40).
                sorted((Comparator.comparing(user -> user.getAge()))).limit(2).forEach(System.out::println);
    }

    @Test
    public void testNum(){
        IntSummaryStatistics intSummaryStatistics = list.stream().mapToInt((User user) -> user.getAge()).summaryStatistics();
        System.out.println("总人数 "+intSummaryStatistics.getCount());
        System.out.println("平均年龄数 "+intSummaryStatistics.getAverage());
        System.out.println("最大年龄 "+intSummaryStatistics.getMax());
        System.out.println("最小年龄 "+intSummaryStatistics.getMin());
        System.out.println("年龄总和 "+intSummaryStatistics.getSum());

    }

    /**
     * map()：接收一个方法作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     */
    @Test
    public void testMap(){
        List<Integer> ages = list.stream().map(user -> user.getAge()).collect(Collectors.toList());
        System.out.println("输出的年纪 "+ages);

        //小写转大写
        List<String> words = Arrays.asList("aaa", "vvvv", "cccc");
        words.stream().map(s->s.toUpperCase()).collect(Collectors.toList()).forEach(System.out::println);
    }

    /**
     * flatMap()：对每个元素执行mapper指定的操作，并用所有mapper返回的Stream中的元素组成一个新的Stream作为最终返回结果，
     * 通俗易懂就是将原来的stream中的所有元素都展开组成一个新的stream
     */

    @Test
    public void testFlatMap(){
        //创建一个 装有两个泛型为integer的集合
        Stream<List<Integer>> listStream = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5));
        Stream<Integer> integerStream = listStream.flatMap((Function<List<Integer>, Stream<Integer>>) i -> i.stream());
        List<Integer> collect = integerStream.collect(Collectors.toList());
        System.out.println(collect.size());
        collect.forEach(System.out::println);
        
    }

    @Test
    public void testJoin(){
        Stream<String> stream = Stream.of("张三", "李四", "王五", "赵六");
        String s = stream.collect(Collectors.joining("-", "(", ")"));
        System.out.println(s);
    }
    @Test
    public void testReduce(){
        Optional<User> reduce = list.stream().reduce((s1, s2) -> {
            return s1.getAge()<s2.getAge()? s1:s2;
        });
        User user = reduce.get();
        System.out.println(user);
    }
}
