package com.gxf.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/11/30 6:01 PM
 **/
public class TestJdkFuture {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    testJdkFuture();
  }


  public static void testJdkFuture() throws ExecutionException, InterruptedException {
    Callable<Integer> callable = () ->{
      Thread.sleep(Long.MAX_VALUE);
      System.out.println("callable do some compute");
      return 1;
    } ;
//    Runnable runnable = () -> {
//      Thread.sleep(100000000);
//      System.out.println("runable do some compute");
//    };
    ExecutorService executorService = Executors.newCachedThreadPool();
    Future<Integer> future = executorService.submit(callable);
//    Future runableFuture = executorService.submit(runnable);
//    Object runableRes = runableFuture.get();
    int res = future.get();
    System.out.println("callable res: " + res);
//    System.out.println("runnable res: " + runableRes);

  }
}
