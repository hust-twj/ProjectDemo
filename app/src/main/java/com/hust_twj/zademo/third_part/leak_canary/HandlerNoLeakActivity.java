package com.hust_twj.zademo.third_part.leak_canary;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import android.util.Log;

import com.hust_twj.zademo.R;

import java.lang.ref.WeakReference;

/**
 * 解决 handler 内存泄露
 * Created by Wenjing.Tang
 * on 2019/8/5
 */
public class HandlerNoLeakActivity extends Activity {

    private final MyHandler mHandler = new MyHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler_no_leak);

        mHandler.sendMessageDelayed(Message.obtain(), 10 * 1000);
    }

    private static class MyHandler extends Handler{

        // 定义 弱引用实例
        private WeakReference<Activity> reference;

        // 在构造方法中传入需持有的Activity实例
        public MyHandler(Activity activity) {
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.d("twj", "收到线程1的消息");
                    break;
             default:
                    break;
            }
        }
    }

}
