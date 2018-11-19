package com.system.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author weiyu
 * @version V1.0
 * @since 2018-11-08
 */
public class ThreadUtil {

    public static void main(String[] args) {
        int b= 0;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));

        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i,b);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();

       Thread t = new Thread();

    }
}


class MyTask implements Runnable {
    private int taskNum;
    private  int i  ;

    public MyTask(int num,int b) {
        this.taskNum = num;
        this.i  = b;
    }

    @Override
    public void run( ) {
        System.out.println("正在执行task "+taskNum);
        try {
            i++;
            Thread.currentThread().sleep(4000);
            System.out.println(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }



}
