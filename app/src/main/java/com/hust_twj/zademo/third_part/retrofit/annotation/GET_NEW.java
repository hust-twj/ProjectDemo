package com.hust_twj.zademo.third_part.retrofit.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-07-11.
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface GET_NEW {

    String value() default "";

}