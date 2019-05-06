package com.hust_twj.zademo.list;

import java.util.Objects;

/**
 * description ：
 * Created by Wenjing.Tang on 2019-05-06.
 */
public class Bean {

    private int id;
    private String name;
    private String address;

    public Bean(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bean)) return false;
        Bean bean = (Bean) o;
        return id == bean.id;
    }
/*
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }*/
}
