package com.gxf.future;

import java.io.IOException;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/11/30 8:02 PM
 **/
public class TestLockSupport {

  public static void main(String[] args) throws IOException {
    Thread t1 = new Thread(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      LockSupport.park();
      System.out.println("t1 thread");
    });
    t1.start();

    Thread t2 = new Thread(() -> {
      LockSupport.unpark(t1);
    });
    t2.start();

    System.in.read();
  }

}
