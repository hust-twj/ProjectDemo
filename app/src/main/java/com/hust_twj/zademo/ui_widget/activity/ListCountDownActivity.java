package com.hust_twj.zademo.ui_widget.activity;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.adapter.CountDownAdapter;
import com.hust_twj.zademo.ui_widget.bean.CountDownBean;

import java.util.ArrayList;
import java.util.List;

/**
 * description ：列表倒计时
 * Created by Wenjing.Tang on 2019-05-24.
 */
public class ListCountDownActivity extends Activity {

    private RecyclerView mRv;
    private CountDownAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_count_down);

        mRv = findViewById(R.id.rv_count_down);

        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CountDownAdapter();
        mAdapter.setData(generateList());
        mRv.setAdapter(mAdapter);

    }

    private List<CountDownBean> generateList() {
        List<CountDownBean> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            CountDownBean bean = new CountDownBean();
            bean.tiotalTime = i * 1000;
            list.add(bean);
        }
        return list;
    }

}
