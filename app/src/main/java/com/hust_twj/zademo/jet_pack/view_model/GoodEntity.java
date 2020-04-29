package com.hust_twj.zademo.jet_pack.view_model;

import androidx.annotation.NonNull;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/4/29.
 */
public class GoodEntity {

    public int resourceID;

    public String url;

    public String name;

    @NonNull
    @Override
    public String toString() {
        return "resourceID : " + resourceID + "\n" +
                "url : " + url + "\n" +
                "name : " + name + "\n";
    }
}
