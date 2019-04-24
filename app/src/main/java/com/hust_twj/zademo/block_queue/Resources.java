package com.hust_twj.zademo.block_queue;

import android.util.Log;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * description ：资源
 * Created by Wenjing.Tang on 2019/4/24.
 */
public class Resources {

    private BlockingQueue<Integer> resourceQueue = new ArrayBlockingQueue<>(10);

    /**
     * 生产者 生产资源
     */
    public void produceRes() {
        try {
            resourceQueue.put(1);
            Log.e("twj124", "生产者 " + Thread.currentThread().getName() + "  生成了一件资源， " +
                    "当前资源池有  " + resourceQueue.size() + "  个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消费者 消费资源
     */
    public void consumeRes() {
        try {
            resourceQueue.take();
            Log.e("twj124", "消费者 " + Thread.currentThread().getName() + "  消耗了一件资源， " +
                    "当前资源池有  " + resourceQueue.size() + "  个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
