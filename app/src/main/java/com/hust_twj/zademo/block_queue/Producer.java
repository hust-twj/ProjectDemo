package com.hust_twj.zademo.block_queue;

import java.util.Random;

/**
 * description ：
 * Created by Wenjing.Tang on 2019/4/19.
 */
public class Producer extends Thread {

    private Resources resources;

    public Producer(Resources resources) {
        this.resources = resources;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 * (new Random().nextInt(10)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resources.produceRes();
        }
    }

}
