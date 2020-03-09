package com.hust_twj.zademo.third_part.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020-01-04.
 */
public interface ServiceInterface {

    //https://api.github.com/users/hust-twj/repos
    //@GET("openapi.do?keyfrom=Yanzhikai&key=2032414398&type=data&doctype=json&version=1.1&q=car")

    // @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    // @FormUrlEncoded
    //Call<Translation> getCall(@Path("user") String user);
    @GET("users")
    Call<List<GitUsers>> getCall();
}
