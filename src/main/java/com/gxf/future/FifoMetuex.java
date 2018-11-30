package com.gxf.future;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/11/30 8:51 PM
 **/
public class FifoMetuex {
  private final AtomicBoolean lock = new AtomicBoolean(false);
  private final Queue<Thread> waiters = new ConcurrentLinkedDeque<>();

  /**
   * 获取变量
   * */
  public void lock(){
    Thread currentThread = Thread.currentThread();
    waiters.add(currentThread);
    while (waiters.peek() != currentThread || lock.compareAndSet(false, true)){
      LockSupport.park(this);
      if (Thread.interrupted()){
        continue;
      }
    }
    //获取到lock
    waiters.remove();
  }

  public void unlock(){
    lock.set(false);
    LockSupport.unpark(waiters.peek());
  }
}
