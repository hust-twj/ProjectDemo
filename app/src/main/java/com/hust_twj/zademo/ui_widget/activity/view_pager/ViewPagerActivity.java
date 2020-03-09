package com.hust_twj.zademo.ui_widget.activity.view_pager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.adapter.MyViewPagerAdapter;
import com.hust_twj.zademo.ui_widget.util.ScaleTransformer;
import com.hust_twj.zademo.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description ：ViewPager2
 * Created by Wenjing.Tang on 2020-2-16.
 */
public class ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.view_pager);

        viewPager.setOffscreenPageLimit(3);
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.img_1);
        list.add(R.drawable.img_2);
        list.add(R.drawable.img_3);
        list.add(R.drawable.img_4);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(this, list);
        viewPager.setAdapter(adapter);

        viewPager.setClipToPadding(false);
        viewPager.setPadding(DensityUtils.dp2px(this, 40), 0,
                DensityUtils.dp2px(this, 40), 0);
        //viewPager.setPageMargin(DensityUtils.dp2px(this, 20));

        viewPager.setPageTransformer(false, new ScaleTransformer());
    }

}
