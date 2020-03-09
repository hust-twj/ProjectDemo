package com.hust_twj.zademo.third_part.retrofit.annotation;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-07-11.
 */
public class AnnoationTest {

    @GET_NEW("www.baidu.com")
    public String getIp(){
        return "1234";
    }

}
