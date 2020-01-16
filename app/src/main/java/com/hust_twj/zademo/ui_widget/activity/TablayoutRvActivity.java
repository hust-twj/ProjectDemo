package com.hust_twj.zademo.ui_widget.activity;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.util.MyHandler;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-11-17.
 */
public class TablayoutRvActivity extends Activity {

    private RecyclerView recyclerView;
    private MyHandler myHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayou_rv);

        recyclerView = findViewById(R.id.rv);

        //滚动显示顶部菜单栏，onScrollStateChanged执行优先于onScrolled方法
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                switch (newState) {
//                    case RecyclerView.SCROLL_STATE_IDLE://滚动停止
//                        if (isClickTab) myHandler.sendEmptyMessage(1);
//                        else myHandler.sendEmptyMessage(0);
//                        break;
//                    case RecyclerView.SCROLL_STATE_DRAGGING://手指 拖动
//                        break;
//                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滚动
//                        break;
//                }
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                scrollHeight += dy; //滑动的距离
//                //滚动距离和导航栏高度算出透明度，实现上滑隐藏，下滑渐现
//                float alp = (float) scrollHeight / (float) DpPxUtils.dp2px(80);
//                mBinding.layoutMenu.setAlpha(alp);
//            }
//        });
    }
}
