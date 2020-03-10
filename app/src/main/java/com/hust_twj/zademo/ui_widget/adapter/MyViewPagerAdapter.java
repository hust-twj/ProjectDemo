package com.hust_twj.zademo.ui_widget.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.hust_twj.zademo.utils.DensityUtils;
import com.hust_twj.zademo.utils.LogUtils;

import java.util.List;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/3/8.
 */
public class MyViewPagerAdapter extends PagerAdapter {
    private List<Integer> list;
    private Context context;

    public MyViewPagerAdapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(context);
        iv.setImageResource(list.get(position));
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
