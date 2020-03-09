package com.hust_twj.zademo.ui_widget.activity.view_pager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.adapter.ViewPager2Adapter;
import com.hust_twj.zademo.ui_widget.util.ScaleInTransformer;
import com.hust_twj.zademo.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description ：ViewPager2
 * Created by Wenjing.Tang on 2020-2-16.
 */
public class ViewPager2Activity extends AppCompatActivity {

    private ViewPager2 mViewPager2;
    private TextView mTvDrag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_2);

        mViewPager2 = findViewById(R.id.view_pager);
        mTvDrag = findViewById(R.id.tv_drag);

        ViewPager2Adapter mAdapter = new ViewPager2Adapter();

        List<Integer> data = new ArrayList<>();
        data.add(0);
        data.add(1);
        data.add(2);
        data.add(3);
        mAdapter.setList(data);

        //设置ViewPager2的滑动方向(水平/垂直)
        //mViewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        //禁止mViewPager2滑动
        //mViewPager2.setUserInputEnabled(false);

        //设置page之间的间距： setPageTransformer
        int margin = DensityUtils.dp2px(this, 10);
       // mViewPager2.setPageTransformer(new MarginPageTransformer(margin));

        //设置一屏多页
        mViewPager2.setOffscreenPageLimit(1);
        RecyclerView recyclerView = (RecyclerView) mViewPager2.getChildAt(0);
        int padding = margin * 2;
        recyclerView.setPadding(padding, 0, padding, 0);
        recyclerView.setClipToPadding(false);

        //设置页面动画：CompositePageTransformer
        CompositePageTransformer  transformer = new CompositePageTransformer();
        transformer.addTransformer(new ScaleInTransformer());
        transformer.addTransformer(new MarginPageTransformer(margin));
        mViewPager2.setPageTransformer(transformer);

        //设置ViewPager2适配器
        mViewPager2.setAdapter(mAdapter);

        //滑动监听
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Toast.makeText(ViewPager2Activity.this, "滑动到" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        //按钮点击可以模拟用户滑动
        mTvDrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager2.beginFakeDrag();
                int screenWidth = DensityUtils.getScreenWidth(ViewPager2Activity.this);
                //滑动：当参数值为正数时表示向前一个页面滑动，当值为负数时表示向下一个页面滑动。
                if (mViewPager2.fakeDragBy(-0.5f * screenWidth)) {
                    mViewPager2.endFakeDrag();
                }
            }
        });

    }

}
