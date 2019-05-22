package com.twj.zademo.jvm;

import java.io.Serializable;

/**
 * description ：
 * Created by Wenjing.Tang on 2019-05-09.
 */
public class Person implements Serializable {

    public String name;

    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }

}
