package com.hust_twj.zademo.third_part.leak_canary;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hust_twj.zademo.R;

/**
 * 非静态内部类 内存泄露
 * Created by Wenjing.Tang
 * on 2019/8/5
 */
public class NonStaticLeakActivity extends Activity {

    //静态变量的生命周期和应用（Application）的生命周期一样长，即比activity生命周期长
    private static Config mConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_static_leak);

        //模拟内存泄露
        if (mConfig == null) {
            mConfig = new Config();
            mConfig.setSize(18);
            mConfig.setTitle("老九门");
        }
        finish();

    }

    class Config {
        private int size;
        private String title;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}
