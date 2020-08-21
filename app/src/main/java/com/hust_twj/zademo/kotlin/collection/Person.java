package com.hust_twj.zademo.kotlin.collection;

import java.util.ArrayList;

/**
 * @author hust_twj
 * @date 2020/8/21
 */
public class Person {

    public String name;

    public int id;

    public static ArrayList<Person> generatePersonList(int capacity) {
        ArrayList<Person> personList = new ArrayList<>();
        if (capacity <= 0) {
            return personList;
        }
        for (int i = 0; i < capacity; i++) {
            Person person = new Person();
            person.name = "name_" + i;
            person.id = i;
            personList.add(person);
        }
        return personList;
    }

}
