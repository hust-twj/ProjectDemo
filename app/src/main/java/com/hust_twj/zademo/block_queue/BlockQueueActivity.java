package com.hust_twj.zademo.block_queue;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hust_twj.zademo.R;

/**
 * description ：生产者/消费者模式
 *
 * 生产者生产数据到缓冲区中，消费者从缓冲区中取数据。
 * 如果缓冲区已经满了，则生产者线程阻塞；如果缓冲区为空，那么消费者线程阻塞。
 *
 * Created by Wenjing.Tang on 2019/4/19.
 */
public class BlockQueueActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_queue);

        Resources resources = new Resources();
        Consumer consumer1 = new Consumer(resources);
        Consumer consumer2 = new Consumer(resources);

        Producer producer1 = new Producer(resources);
        Producer producer2 = new Producer(resources);
        Producer producer3 = new Producer(resources);

        consumer1.start();
        consumer2.start();
        producer1.start();
        producer2.start();
        producer3.start();

    }

}
