package com.hust_twj.zademo.hook;

import android.content.Context;
import android.view.View;

import com.hust_twj.zademo.utils.LogUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/3/31.
 */
public class HookSetOnClickListenerHelper {

    public static void hook(Context context, View view) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException, NoSuchFieldException {

        // 反射执行View类的getListenerInfo()方法，拿到v的mListenerInfo对象，这个对象就是点击事件的持有者
        try {
            //View getView = View.class
            //@SuppressLint("DiscouragedPrivateApi")
            Method method = View.class.getDeclaredMethod("getListenerInfo");
            method.setAccessible(true);

            //这里拿到的就是mListenerInfo对象，也就是点击事件的持有者
            Object mListenerInfo = method.invoke(view);

            //要从这里面拿到当前的点击事件对象
            Class<?> clz = Class.forName("android.view.View$ListenerInfo");
            Field field = clz.getDeclaredField("mOnClickListener");

            //取得真实的mOnClickListener对象
            final View.OnClickListener clickListener = (View.OnClickListener) field.get(mListenerInfo);

            //InvocationHandler: 当我们通过代理对象调用一个方法的时候，这个方法的调用就会被转发为由InvocationHandler这个接口的 invoke 方法来进行调用
            Object proxyClickListener = Proxy.newProxyInstance(context.getClass().getClassLoader(),
                    new Class[]{View.OnClickListener.class},
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            //加入自己的逻辑
                            LogUtils.e("twj124", "点击事件被hook到了");
                           // LogUtils.e("twj124", clickListener + "  " + proxy);

                            return method.invoke(clickListener, args);
                        }
                    });
            field.set(mListenerInfo, proxyClickListener);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
