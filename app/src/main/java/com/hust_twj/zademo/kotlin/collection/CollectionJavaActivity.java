package com.hust_twj.zademo.kotlin.collection;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hust_twj.zademo.R;

import java.util.ArrayList;

/**
 * @author hust_twj
 * @date 2020/8/21
 */
public class CollectionJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coroutine);

        Log.e("twj124", findPerson(1, Person.generatePersonList(20)).name);
    }

    //1. 遍历
    private Person findPerson(int personID, ArrayList<Person> personList) {
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            if (person.id == personID) {
                return person;
            }
        }
        return null;
    }

    //2. 遍历
    private void forEach(ArrayList<Person> personList) {
        for (int i = 0; i < personList.size(); i++) {
            Log.e("twj124", personList.get(i).id + " " + personList.get(i).name);
        }

        for (Person person : personList) {
            Log.e("twj124", person.id + " " + person.name);
        }
    }
}
