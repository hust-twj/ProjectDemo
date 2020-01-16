package com.hust_twj.zademo.ui_widget.activity;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.widget.CircleRecyclerView;
import com.hust_twj.zademo.utils.LogUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * description ：圆弧RV
 * Created by Wenjing.Tang on 2019-05-28.
 */
public class ArcRecycleViewActivity extends Activity {

    private CircleRecyclerView mCircleRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private List<Integer> mImgList;

    private Integer[] mImgs = {
            R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_5, R.drawable.img_6, R.drawable.img_7, R.drawable.img_8,
            R.drawable.img_9, R.drawable.img_10, R.drawable.img_11, R.drawable.img_12
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_acr_rv);

        mCircleRecyclerView = findViewById(R.id.rv_acr);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        mCircleRecyclerView.setLayoutManager(mLayoutManager);
        mCircleRecyclerView.setNeedLoop(true);

        mImgList = Arrays.asList(mImgs);
        Collections.shuffle(mImgList);

        mCircleRecyclerView.setAdapter(new A());
    }

    class A extends RecyclerView.Adapter<VH> {

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            VH h = null;
            if (mCircleRecyclerView.getLayoutManager().canScrollHorizontally()) {
                h = new VH(LayoutInflater.from(ArcRecycleViewActivity.this)
                        .inflate(R.layout.item_h, parent, false));
            } else if (mCircleRecyclerView.getLayoutManager().canScrollVertically()) {
                    h = new VH(LayoutInflater.from(ArcRecycleViewActivity.this)
                            .inflate(R.layout.item_v, parent, false));
            }
            return h;
        }

        @Override
        public void onBindViewHolder(final VH holder, int position) {
            holder.tv.setText("Number :" + (position % mImgList.size()));
            holder.iv.setBackground(getResources().getDrawable(mImgList.get(position % mImgList.size())));

            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.e("twj124", holder.tv.getText());
                }
            });
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }
    }


    class VH extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView iv;

        public VH(View itemView) {
            super(itemView);
            tv =itemView.findViewById(R.id.item_text);
            iv = itemView.findViewById(R.id.item_img);
        }
    }
}
