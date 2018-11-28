package com.gxf.future;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/**
 * @Author: <guanxianseng@163.com>
 * @Description:
 * @Date: Created in : 2018/11/28 3:36 PM
 **/
public class GxfInteger {
  private sun.misc.Unsafe U;
  private long offset;
  private Integer value = 0;
  private static Object lock = new Object();

  public GxfInteger(int value) throws Exception {
    this.value = value;
    Field f = Unsafe.class.getDeclaredField("theUnsafe");
    f.setAccessible(true);
    U = (Unsafe) f.get(null);
    offset = U.objectFieldOffset(GxfInteger.class.getDeclaredField("value"));
  }

  public void increament(){
    value ++;
  }

  public void lockAdd(){
    synchronized (lock){
      value++;
    }
  }

  public void casIncreament(){
    boolean update = false;
    do{
      update = U.compareAndSwapObject(this, offset, value, value + 1);
    }while (!update);
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
