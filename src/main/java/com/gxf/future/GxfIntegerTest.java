package com.gxf.future;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/11/28 3:37 PM
 **/
public class GxfIntegerTest {

  public static void main(String[] args) throws InterruptedException, Exception {
    GxfInteger gxfInteger = new GxfInteger(0);
    int threadNum = 100;
    Runnable add = () -> {
      for(int i = 0; i < 10000; i++){
//        gxfInteger.increament();
        gxfInteger.casIncreament();
//        gxfInteger.lockAdd();
      }
    };
    long start = System.currentTimeMillis();

    Thread[] threads = new Thread[threadNum];
    for(int i = 0; i < threads.length; i++){
      threads[i] = new Thread(add);
    }
    for(int i = 0; i < threadNum; i++){
      threads[i].start();
    }
    for(int i = 0; i < threadNum; i++){
      threads[i].join();
    }
    System.out.println("time cost : " + (System.currentTimeMillis() - start));
//    Thread.sleep(10000);
    System.out.println("result: " + gxfInteger.getValue());
  }
}
