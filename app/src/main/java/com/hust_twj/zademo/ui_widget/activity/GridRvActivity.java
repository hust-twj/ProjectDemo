package com.hust_twj.zademo.ui_widget.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.adapter.GridRvAdapter;
import com.hust_twj.zademo.ui_widget.bean.GridMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * description ：双层列表
 * Created by Wenjing.Tang on 2019-05-24.
 */
public class GridRvActivity extends Activity {

    private RecyclerView mRv;
    private GridRvAdapter mAdapter;

    private String[] mItemTexts = new String[]{"朋友圈 ", "腾讯QQ", "QQ空间", "腾讯微博",
            "微信好友", "新浪微博", "新浪微博1", "新浪微博2", "新浪微博2"};

    private int[] mItemImgs = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_rv);

        mRv = findViewById(R.id.rv);

        List<GridMenu> mData = generateList();
        int span = mData.size() / 2;

        mRv.setClipToPadding(false);
        mRv.setClipChildren(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, span);
        //mRv.setLayoutManager(gridLayoutManager);

        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new GridRvAdapter(this);
        mAdapter.setData(mData);
        mRv.setAdapter(mAdapter);

    }

    private List<GridMenu> generateList() {
        List<GridMenu> list = new ArrayList<>();
        for (int i = 1; i < mItemTexts.length; i++) {
            GridMenu bean = new GridMenu();
            bean.imgId = mItemImgs[i];
            bean.label = mItemTexts[i];
            list.add(bean);
            list.add(bean);
            list.add(bean);
        }
        return list;
    }

}
