package cn.trusteye.concurrent.phase2.chapter2;

import java.util.concurrent.TimeUnit;

/**
 * @author rayman
 * @date 12:14
 */
public class VolidateClass {
    private volatile static int initValue = 0;
    private final static int MAX_VALUE = 5;

    public static void main(String[] args) {
        new Thread(()->{
            int local_value = initValue;
            while(true){
                if(local_value!= initValue){
                    local_value = initValue;
                    System.out.println(Thread.currentThread().getName() + " find new value:" + local_value);
                }
            }

        },"judge").start();

/*
        new Thread(()->{
            for (initValue = 0; initValue < MAX_VALUE; initValue++) {
                System.out.println(Thread.currentThread().getName()+" change value:"+initValue);
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"update").start();
*/
        new Thread(()->{
            while (initValue< MAX_VALUE){
                System.out.println(Thread.currentThread().getName()+" change value:"+(++initValue));
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"update").start();
    }
}
