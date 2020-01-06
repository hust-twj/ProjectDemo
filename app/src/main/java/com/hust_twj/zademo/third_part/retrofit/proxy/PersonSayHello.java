package com.hust_twj.zademo.third_part.retrofit.proxy;

import android.util.Log;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020-01-06.
 */
public class PersonSayHello implements Hello {

    @Override
    public void morning(String name) {
        Log.e("twj124", "PersonSayHello morning  " + name);

    }
}