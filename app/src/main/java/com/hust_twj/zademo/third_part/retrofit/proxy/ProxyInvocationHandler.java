package com.hust_twj.zademo.third_part.retrofit.proxy;

import android.util.Log;

import com.hust_twj.zademo.utils.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description ：代理InvocationHandler
 * Created by Wenjing.Tang on 2020-01-06.
 */
public class ProxyInvocationHandler implements InvocationHandler {

    private Hello hello;

    public ProxyInvocationHandler(Hello hello) {
        this.hello = hello;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e("twj124", "invoke");
        Object result = null;
        if (method.getName().equals("morning")) {
            Log.e("twj124", "args: " + args[0]);

            Log.e("twj124", "invoke before");
            result = method.invoke(hello, args);
            Log.e("twj124", "invoke after");
        }
        return result;
    }

}
