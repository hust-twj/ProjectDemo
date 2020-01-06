package com.hust_twj.zademo.third_part.retrofit.proxy;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hust_twj.zademo.R;

import java.lang.reflect.Proxy;

/**
 * Description ：动态代理
 * Created by Wenjing.Tang on 2020-01-06.
 */
public class ProxyActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);

        ProxyInvocationHandler handler = new ProxyInvocationHandler(new PersonSayHello());
        Hello sub = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(), // 传入ClassLoader，接口类的ClassLoader
                new Class[] { Hello.class }, // 传入要实现的接口
                handler); // 传入处理调用方法的InvocationHandler
        sub.morning("Bob");
    }

}
