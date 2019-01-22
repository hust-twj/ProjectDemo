package com.hust_twj.zademo.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

import java.lang.ref.WeakReference;

public class HandlerActivity extends Activity implements View.OnClickListener {

    private TextView mTvSend, mTvReceive, mTvUpdate;


    private MyHandler myHandler;
    private static final int MSG_UPDATE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler);

        mTvSend = findViewById(R.id.tv_send);
        mTvReceive = findViewById(R.id.tv_receive);
        mTvUpdate = findViewById(R.id.tv_update);

        mTvSend.setOnClickListener(this);
        mTvUpdate.setOnClickListener(this);
        findViewById(R.id.tv_create_handler).setOnClickListener(this);

        myHandler = new MyHandler(this);

    }

    private static class MyHandler extends Handler {

        //MyHandler 弱引用 HandlerActivity
        private WeakReference<HandlerActivity> mActivity;

        public MyHandler(HandlerActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_UPDATE:
                    mActivity.get().mTvSend.setText("haha2");
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_send:

                break;
            case R.id.tv_update:
                myHandler.sendEmptyMessage(MSG_UPDATE);
                break;
            case R.id.tv_create_handler:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //需要加Looper.prepare(); 不然会在低版本上报错；高版本为啥不会？
                        Looper.prepare();
                        Handler handler = new Handler(){
                            @Override
                            public void handleMessage(Message msg) {
                                //super.handleMessage(msg);
                                LogUtils.e("twj124", "handleMessage");
                                Toast.makeText(getApplicationContext(), "handler msg", Toast.LENGTH_LONG).show();
                            }
                        };
                        handler.sendEmptyMessage(1);
                        //需要加Looper.loop() 需要放在发送消息之后
                        Looper.loop();
                    }
                }).start();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
