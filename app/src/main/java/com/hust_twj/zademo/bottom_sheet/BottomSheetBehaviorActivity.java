package com.hust_twj.zademo.bottom_sheet;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.main.MainAdapter;
import com.hust_twj.zademo.utils.LogUtils;


public class BottomSheetBehaviorActivity extends Activity implements View.OnClickListener {

    private ImageView mIv;
    private RecyclerView mRv;
    private NestedScrollView mScrollView;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_behavior);

        initViews();
        initViewData();
        bindListeners();
    }

    private void initViews() {
        mIv = findViewById(R.id.iv);
        mRv = findViewById(R.id.rv_list);
        mScrollView = findViewById(R.id.nested_scroll_view);
    }

    private void initViewData() {
        bottomSheetBehavior = BottomSheetBehavior.from(mScrollView);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        MainAdapter adapter = new MainAdapter(this);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(adapter);
    }

    private void bindListeners() {
        mIv.setOnClickListener(this);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                LogUtils.e("twj124","onStateChanged  " +  newState);
                //if (newState != BottomSheetBehavior.STATE_DRAGGING) {
                    ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();
                    if (bottomSheet.getHeight() > 1800) {
                        layoutParams.height = 1800 ;
                        bottomSheet.setLayoutParams(layoutParams);
                    }
                //}
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset <= 0) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                LogUtils.e("twj124", "onSlide  " +  slideOffset);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
                default:
                    break;
        }

    }
}
