package com.hust_twj.zademo.hook;

import com.hust_twj.zademo.utils.LogUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/4/30.
 */
@Aspect
public class AspectTest {

    final String TAG = "twj124";

   // @Before("execution(* *..ImageView+.setImage**(..))")
   @Before("execution(* *..MainActivity+.on**(..))")
    public void method(JoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getThis().getClass().getSimpleName();

       // LogUtils.e(TAG, "class:" + className);
        LogUtils.e(TAG, "method:" + methodSignature.getName());
    }

}
