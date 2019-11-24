package com.sls.jdk8.completAbleFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class TestCompletAbleFucture {

   public static void main(String[] args) throws ExecutionException, InterruptedException {
      CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> System.out.println("runAsync"));
      CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {return 10/0;}).
              whenCompleteAsync((u,v)->{   //  whenComplete(BiConsumer<? super T,? super Throwable> action)  会获取上一步计算的计算结果和异常信息。
         System.out.println(u);
         System.out.println(v);
      }).exceptionally((e)->{e.printStackTrace(); return    // exceptionally(Function<Throwable,? extends T> fn)该方法是对异常情况的处理，当函数异常时应该的返回值
      10;});

//      System.out.println(future1.get());
      System.out.println(future2.get());

//      testThenApply();
      System.out.println("********************");
//      testThenAccept();
      testThenAcceptBoth();
      System.out.println("********************");
      testRunAfterBoth();
      System.out.println("********************");
      testThenCombine();

      allOf();
   }

   /**
    * 执行异步任务有四个方法
    *
    *
    * 没有返回值
    * public static CompletableFuture<Void> runAsync(Runnable runnable)  ###
    *
    * public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
    *
    * Runnable 中有一个run()方法无返回值
    *
    * =======================================================================================
    *
    *
    *有返回值
    * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) ####
    *
    * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
    *
    *Supplier 中有一个T get()方法有返回值
    *
    *
    * 以 Async 结尾并且没有 Executor 参数的，会默认使用 ForkJoinPool.commonPool() 作为它的线程池执行异步代码。
    * 以run开头的，因为以 Runable 类型为参数所以没有返回值。
    *
    */


   /**
    * 结果处理转换 thenApply
    *
    * CompletableFuture 由于有回调，可以不必等待一个计算完成而阻塞着调用线程，可以在一个结果计算完成之后紧接着执行某个Action。
    * 我们可以将这些操作串联起来。
    *
    * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
    * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
    * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
    *
    */

   public static void testThenApply() throws ExecutionException, InterruptedException {

      CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()->1).
              thenApply((a)->a*5).  //5
              thenApply((a)->a+6).  //5+6
              thenApply((a)->a-1);  //5+6-1

      System.out.println(future.get());
   }

   /**
    *
    * thenAccept/thenAcceptBoth/thenRun
    * 单纯的去消费结果而不会返回新的值，因些计算结果为 Void;
    *
    * public CompletableFuture<Void> thenAccept(Consumer<? super T> action)
    * public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action)
    * public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor)
    *
    */

   public static void testThenAccept(){

      CompletableFuture<Void> future = CompletableFuture.supplyAsync(()->2).
              thenAccept((a)->{
                 System.out.println(a*4);
              }).
              thenAccept((b)->{
                   System.out.println(b);
      });

   }

   public static void testThenAcceptBoth() throws ExecutionException, InterruptedException {

    CompletableFuture.supplyAsync(()->2).
              thenApply((a)->{
                 System.out.println(a*4);
                 return a*4;
              }).thenAcceptBoth(CompletableFuture.supplyAsync(()->3),(a,b)->{
                  System.out.println(a);
                  System.out.println(b);
      }).get();

   }

   public static void testRunAfterBoth() throws ExecutionException, InterruptedException {

    CompletableFuture.supplyAsync(()->{
       return 2;
    }).runAfterBoth(CompletableFuture.supplyAsync(()->1),()->{
       System.out.println("111");
              }).get();

   }

   /**
    *  thenCompose/thenCombine
    *
    *  public <U> CompletableFuture<U> thenCompose(Function<? super T,? extends CompletionStage<U>> fn)
    * public <U> CompletableFuture<U> thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn)
    * public <U> CompletableFuture<U> thenComposeAsync(Function<? super T,? extends CompletionStage<U>> fn, Executor executor)
    *以上接收类型为 Function<? super T,? extends CompletionStage<U>> fn ,fn 接收上一级返回的结果，
    * 并返回一个新的 CompletableFuture
    */

   public static  void testCompose() throws ExecutionException, InterruptedException {
      CompletableFuture<Integer> future = CompletableFuture
              .supplyAsync(() -> 1)
              .thenApply((a) -> {
                 try {
                    Thread.sleep(1000);
                 } catch (InterruptedException e) {
                    e.printStackTrace();
                 }
                 return a + 10;
              })
              .thenCompose((s) -> {
                 System.out.println(s); //11
                 return CompletableFuture.supplyAsync(() -> s * 5);
              });

      System.out.println(future.get());//55
   }
   /**
    * public <U,V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
    * public <U,V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
    * public <U,V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn, Executor executor)
    * 两个CompletionStage是并行执行的，它们之间并没有先后依赖顺序，other并不会等待先前的CompletableFuture执行完毕后再执行
    *
    */
   public static void testThenCombine() throws ExecutionException, InterruptedException {

      Random random = new Random();
      CompletableFuture<Integer> future = CompletableFuture
              .supplyAsync(() -> {
                 try {
                    Thread.sleep(random.nextInt(1000));
                 } catch (InterruptedException e) {
                    e.printStackTrace();
                 }
                 System.out.println("supplyAsync");
                 return 2;
              }).thenApply((a) -> {
                 try {
                    Thread.sleep(random.nextInt(1000));
                 } catch (InterruptedException e) {
                    e.printStackTrace();
                 }
                 System.out.println("thenApply");
                 return a * 3;
              })
              .thenCombine(CompletableFuture.supplyAsync(() -> {
                 try {
                    Thread.sleep(random.nextInt(1000));
                 } catch (InterruptedException e) {
                    e.printStackTrace();
                 }
                 System.out.println("thenCombineAsync");
                 return 10;
              }), (a, b) -> {
                 System.out.println(a);
                 System.out.println(b);
                 return a + b;
              });

      System.out.println(future.get());
   }

   /**
    * acceptEither 无返回值
    * applyToEither 有返回值
    * public CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action)
    * public CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action)
    * public CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor)
    *
    */

   public static void acceptEither() throws ExecutionException, InterruptedException {
      Random random = new Random();
      CompletableFuture
              .supplyAsync(() -> {
                 try {
                    Thread.sleep(random.nextInt(1000));
                 } catch (InterruptedException e) {
                    e.printStackTrace();
                 }
                 return "A";
              })
              .acceptEither(CompletableFuture.supplyAsync(() -> {
                 try {
                    Thread.sleep(random.nextInt(1000));
                 } catch (InterruptedException e) {
                    e.printStackTrace();
                 }
                 return "B";
              }), System.out::println)
              .get();
   }

   /**
    * allOf 有时1 2
    *
    * anyOf  1 or 2
    * public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)
    */

   public static void allOf() throws ExecutionException, InterruptedException {

      Random random = new Random();
      CompletableFuture.allOf(
     /*CompletableFuture.anyOf(*/
              CompletableFuture.runAsync(() -> {
                 try {
                    Thread.sleep(random.nextInt(1000));
                 } catch (InterruptedException e) {
                    e.printStackTrace();
                 }
                 System.out.println(1);
              }),
              CompletableFuture.runAsync(() -> {
                 try {
                    Thread.sleep(random.nextInt(1000));
                 } catch (InterruptedException e) {
                    e.printStackTrace();
                 }
                 System.out.println(2);
              }))
              .get();

   }

}
