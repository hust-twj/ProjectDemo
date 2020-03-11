package com.hust_twj.zademo.ui_widget.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.adapter.RvScrollerAdapter;
import com.hust_twj.zademo.ui_widget.widget.BottomSmoothScroller;
import com.hust_twj.zademo.ui_widget.widget.TopSmoothScroller;

/**
 * Description ：RecycleView滚动到指定位置
 * Created by Wenjing.Tang on 2020/3/11.
 */
public class RvScrollerActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "twj124";
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_scroller);

        recyclerView = findViewById(R.id.rv);
        RvScrollerAdapter adapter = new RvScrollerAdapter(this);
        recyclerView.setAdapter(adapter);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        findViewById(R.id.btn_scroll_1).setOnClickListener(this);
        findViewById(R.id.btn_scroll_2).setOnClickListener(this);
        findViewById(R.id.btn_scroll_top).setOnClickListener(this);
        findViewById(R.id.btn_scroll_bottom).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scroll_1:
                int position = (int) (Math.random() * 35);
                Toast.makeText(RvScrollerActivity.this, "滑到：" + position, Toast.LENGTH_SHORT).show();
                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(RvScrollerActivity.this);
                smoothScroller.setTargetPosition(position);
                manager.startSmoothScroll(smoothScroller);
                break;
            case R.id.btn_scroll_2:
                int position2 = (int) (Math.random() * 35);
                Toast.makeText(RvScrollerActivity.this, "闪到：" + position2, Toast.LENGTH_SHORT).show();
                manager.scrollToPositionWithOffset(position2, 0);
                break;
            case R.id.btn_scroll_top:
                int position3 = (int) (Math.random() * 35);
                Toast.makeText(RvScrollerActivity.this, "顶部 ：" + position3, Toast.LENGTH_SHORT).show();
                TopSmoothScroller topSmoothScroller = new TopSmoothScroller(this);
                topSmoothScroller.setTargetPosition(position3);
                manager.startSmoothScroll(topSmoothScroller);
                break;

            case R.id.btn_scroll_bottom:
                int position4 = (int) (Math.random() * 35);
                Toast.makeText(RvScrollerActivity.this, "底部 ：" + position4, Toast.LENGTH_SHORT).show();
                BottomSmoothScroller bottomSmoothScroller = new BottomSmoothScroller(this);
                bottomSmoothScroller.setTargetPosition(position4);
                manager.startSmoothScroll(bottomSmoothScroller);
                break;
        }

    }
}