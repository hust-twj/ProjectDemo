package com.hust_twj.zademo.ui_widget.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.widget.CircleRecyclerView;
import com.hust_twj.zademo.ui_widget.widget.CircularHorizontalMode;
import com.hust_twj.zademo.ui_widget.widget.ItemViewMode;
import com.hust_twj.zademo.utils.DensityUtils;
import com.hust_twj.zademo.utils.LogUtils;

/**
 * description ：双层列表
 * Created by Wenjing.Tang on 2019-05-24.
 */
public class GridRvActivity extends Activity {

    private CircleRecyclerView mCircleRecyclerView;

    private ItemViewMode mItemViewMode;
    private LinearLayoutManager mLayoutManager;
    private static final int SPAN_COUNT = 2;

    private int[] mImgs = {R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_5, R.drawable.img_6, R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_5, R.drawable.img_6, R.drawable.img_1, R.drawable.img_2};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_rv);

        mCircleRecyclerView = findViewById(R.id.rv_acr);

        mItemViewMode = new CircularHorizontalMode();
        mLayoutManager = new GridLayoutManager(this, SPAN_COUNT, LinearLayoutManager.HORIZONTAL, false);

        mCircleRecyclerView.addItemDecoration(new ItemDecoration());

        mCircleRecyclerView.setLayoutManager(mLayoutManager);
        mCircleRecyclerView.setViewMode(mItemViewMode);
        mCircleRecyclerView.setNeedLoop(true);
        mCircleRecyclerView.setDrawBg(true);
        mCircleRecyclerView.setAdapter(new A());

    }

    class A extends RecyclerView.Adapter<VH> {

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            VH h = new VH(LayoutInflater.from(GridRvActivity.this).inflate(R.layout.item_h, parent, false));
            return h;
        }

        @Override
        public void onBindViewHolder(final VH holder, final int position) {
            holder.tv.setText(position + "");
            holder.iv.setBackground(getResources().getDrawable(mImgs[position]));

            holder.iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.e("twj124", holder.tv.getText() + "  pos: " + position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mImgs.length;
        }
    }

    class VH extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView iv;

        public VH(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.item_text);
            iv = itemView.findViewById(R.id.item_img);
        }
    }

    private class ItemDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
            int spanCount = getSpanCount(parent);
            int childCount = parent.getAdapter().getItemCount();

            int padding = DensityUtils.dp2px(GridRvActivity.this, 40);
            outRect.left = padding;
            boolean islastRow = isLastRow(parent, itemPosition, SPAN_COUNT, childCount);
            if (islastRow) {
                outRect.right = padding;
            }
        }
    }

    private boolean isLastColumn(RecyclerView parent, int pos, int spanCount,
                                 int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0) {// 如果是最后一列，则不需要绘制右边
                return true;
            }
        }
        return false;
    }

    private boolean isLastRow(RecyclerView parent, int pos, int spanCount,
                              int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int lines = childCount % spanCount == 0 ? childCount / spanCount : childCount / spanCount + 1;
            return lines == pos / spanCount + 1;
        }
        return false;
    }

    private boolean isFirstRow(RecyclerView parent, int pos, int spanCount,
                               int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos / spanCount + 1) == 1) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    //获取列数
    private int getSpanCount(RecyclerView parent) {
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {

            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

}
