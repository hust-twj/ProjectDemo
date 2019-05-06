package com.hust_twj.zademo.list;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by wenjing.tang on 2018/8/2.
 */

public class ListActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        List<String> list = new ArrayList<>();
        list.add("twj");

        LogUtils.e("twj123", list.toString());

        list.add(1, "twj2");
        LogUtils.e("twj123", list.toString());

        TextView textView = findViewById(R.id.tv_list);
        textView.setText(list.toString());

        //交集

        //并集

        //差集
        List<String> aList = new ArrayList<>();
        aList.add("1");
        aList.add("2");
        List<String> bList = new ArrayList<>();
        bList.add("2");
        bList.add("3");
        bList.add(null);
        aList.removeAll(bList);
        LogUtils.e("twj123", aList.toString());

    }
}
