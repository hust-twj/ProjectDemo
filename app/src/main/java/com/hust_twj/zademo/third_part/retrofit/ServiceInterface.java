package com.hust_twj.zademo.third_part.retrofit;

import retrofit2.Call;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020-01-04.
 */
public interface ServiceInterface {

    @GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")
    Call<Translation> getCall();
}
