package com.hust_twj.zademo.list;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.ClickUtils;
import com.hust_twj.zademo.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
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
        //LogUtils.e("twj123", list.toString());

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
        //LogUtils.e("twj123", aList.toString());

        ArrayList<Bean> allStudents = new ArrayList<>();
        ArrayList<Bean> boyStudents = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            Bean bean = new Bean(i, "name is " + i, "address is " + i);
            allStudents.add(bean);

        }


        for (int i = 0; i < 5; i++) {
            Bean bean = new Bean(i, "name is " + i, "address is " + i);
            boyStudents.add(bean);

        }

        //不重写equals()无法删除
        LogUtils.e("twj123", "allStudents.size()------before-------------->" + allStudents.size());
        LogUtils.e("twj123", "remove result : " + allStudents.removeAll(boyStudents));
        LogUtils.e("twj123", "allStudents.size()-------after-------------->" + allStudents.size());

        //只重写equals()，不hashcode（），get获取不到值
        HashMap<Bean,String> map = new HashMap<>();
        for (int i = 0; i < 10 ; i++) {
            Bean  bean = new Bean(i,"name is "+i,"address is "+i);
            map.put(bean,"i :"+i);
        }
        Bean bean = new Bean(1,"name is 1","address is 1");
        //不hashcode（），map.get(bean) 返回null；重写后返回1
        LogUtils.e("twj123", " allStudents.get(bean)----------------------->"+ map.get(bean));

    }

    public void test(View view) {
        LogUtils.e("twj125", ClickUtils.isFastClick());

    }

}
