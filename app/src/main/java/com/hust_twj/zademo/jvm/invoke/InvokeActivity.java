package com.hust_twj.zademo.jvm.invoke;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射
 * Created by Wenjing.Tang
 * on 2019/7/12
 */
public class InvokeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_invoke);

        invokePrivateMethod();

        invokeStaticMethod();
    }

    /**
     * 反射调用私有方法
     */
    private void invokePrivateMethod() {
        Class<?> clz;
        try {
            InvokeTest invokeTest = new InvokeTest();
            clz = invokeTest.getClass();
            Method method = clz.getDeclaredMethod("add", int.class, int.class);

            //压制Java对访问修饰符的检查!!!
            method.setAccessible(true);
            //反射调用私有方法
            LogUtils.e("twj124","反射调用私有方法:" + method.invoke(invokeTest, 1, 2));

            //访问私有属性
            Field  field = clz.getDeclaredField("tag");
            //压制Java对访问修饰符的检查!!!
            field.setAccessible(true);
            field.set(invokeTest, "new tag");
            LogUtils.e("twj124","反射调用私有属性:" + field.getName() + "  "  + invokeTest.getTag());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射调用静态方法
     */
    private void invokeStaticMethod() {
        Class<?> clz;
        try {
            clz = Class.forName("java.lang.Math");
            //"abs" ：方法名；long.class：参数类型
            Method method = clz.getMethod("abs", long.class);
            //invoke的一个参数是null，因为这是静态方法，不需要通过实例运行
            LogUtils.e("twj124","反射调用静态方法:" + method.invoke(null, -110));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
