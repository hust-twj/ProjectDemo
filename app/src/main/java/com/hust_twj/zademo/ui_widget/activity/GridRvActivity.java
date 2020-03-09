package com.hust_twj.zademo.ui_widget.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.widget.CircleRecyclerView;
import com.hust_twj.zademo.utils.DensityUtils;
import com.hust_twj.zademo.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * description ：双层列表
 * Created by Wenjing.Tang on 2019-05-24.
 */
public class GridRvActivity extends Activity {

    private CircleRecyclerView mCircleRecyclerView;

    private LinearLayoutManager mLayoutManager;
    private static final int SPAN_COUNT = 2;

    private int[] mImgs = {R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img_5,
            R.drawable.img_6, R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4,
            R.drawable.img_5, R.drawable.img_6, R.drawable.img_1, R.drawable.img_2};

    private String[] mIndex = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_rv);

        mCircleRecyclerView = findViewById(R.id.rv_acr);

        mLayoutManager = new GridLayoutManager(this, SPAN_COUNT, LinearLayoutManager.HORIZONTAL, false);

        mCircleRecyclerView.addItemDecoration(new ItemDecoration());

        mCircleRecyclerView.setLayoutManager(mLayoutManager);
        mCircleRecyclerView.setNeedLoop(true);
        mCircleRecyclerView.setDrawBg(true);
        mCircleRecyclerView.setAdapter(new A());

        List<String> list = new ArrayList<>();

        for (int i = 0; i < mImgs.length -1; i++) {
            list.add(String.valueOf(i));
        }

        LogUtils.a("twj", list.toString());
        convertData(list);
    }

    /**
     * 将横向数据转换为纵向数据
     */
    private List<String> convertData(List<String> original) {
        //结果
        List<String> result = new ArrayList<>();
        //第1行
        List<String> firstRow = new ArrayList<>();
        //第2行
        List<String> secondRow = new ArrayList<>();

        if (original.size() == 0) {
            return result;
        }
        //列数
        int span = (original.size() + 1) / 2;

        for (int i = 0; i < span; i++) {
            firstRow.add(original.get(i));
        }

        for (int i = span; i < original.size(); i++) {
            secondRow.add(original.get(i));
        }

        LogUtils.a("twj", firstRow.toString() +" ||  " +  secondRow.toString());
        // [0, 1, 2, 3, 4, 5, 6]

        for (int i = 0; i < firstRow.size(); i++) {
            if (i % 2 == 1) {
                firstRow.add(i, secondRow.get(0));
                secondRow.remove(0);
            }
        }

        if (secondRow.size() != 0) {
            firstRow.addAll(secondRow);
        }

        LogUtils.a("twj", firstRow.toString() );

        return result;
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

    public static class Menu {
        String image;
        String title;
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
