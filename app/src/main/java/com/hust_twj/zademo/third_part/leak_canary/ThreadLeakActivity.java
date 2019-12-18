package com.hust_twj.zademo.third_part.leak_canary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hust_twj.zademo.R;

/**线程引起的内存泄露
 *
 * Created by Wenjing.Tang
 * on 2019/8/5
 */
public class ThreadLeakActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_leak);

        new MyThread().start();

        new MyThread2().start();
    }

    public class MyThread extends Thread  {
        @Override
        public void run() {
            try {
                Thread.sleep(10 * 1000);
                Log.i("twj", "执行了子线程");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class MyThread2 extends Thread  {
        @Override
        public void run() {
            try {
                Thread.sleep(10 * 1000);
                Log.i("twj", "执行了子线程");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
