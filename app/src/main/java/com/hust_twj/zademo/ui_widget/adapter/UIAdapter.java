package com.hust_twj.zademo.ui_widget.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.main.IItemHelper;
import com.hust_twj.zademo.ui_widget.bean.UIEntity;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Wenjing.Tang
 * on 2019/1/8
 */
public class UIAdapter extends RecyclerView.Adapter<UIAdapter.ViewHolder> implements IItemHelper {

    private Context mContext;
    private ArrayList<UIEntity> mDataList;

    public UIAdapter(Context context) {
        mContext = context;

        initData();
    }

    private void initData() {
        ArrayList<UIEntity> mDataList = new ArrayList<>();

        UIEntity entity0 = new UIEntity();
        entity0.index = UIEntity.INDEX_SEEK_BAR;
        entity0.title = "SeekBar";
        mDataList.add(entity0);

        UIEntity entity1 = new UIEntity();
        entity1.index = UIEntity.INDEX_PROGRESS_BAR;
        entity1.title = "ProgressBar";
        mDataList.add(entity1);

        UIEntity entity2 = new UIEntity();
        entity2.index = UIEntity.INDEX_GRADIENT_RING;
        entity2.title = "渐变圆环";
        mDataList.add(entity2);

        UIEntity entity3 = new UIEntity();
        entity3.index = UIEntity.INDEX_FRAGMENT;
        entity3.title = "fragment";
        mDataList.add(entity3);

        UIEntity entity4 = new UIEntity();
        entity4.index = UIEntity.INDEX_COUNT_DOWN;
        entity4.title = "列表倒计时";
        mDataList.add(entity4);

        UIEntity entity5 = new UIEntity();
        entity5.index = UIEntity.INDEX_ARC_RING;
        entity5.title = "圆弧RV";
        mDataList.add(entity5);

        UIEntity entity6 = new UIEntity();
        entity6.index = UIEntity.INDEX_ELLIPSE_RV;
        entity6.title = "椭圆环菜单";
        mDataList.add(entity6);

        UIEntity entity7 = new UIEntity();
        entity7.index = UIEntity.INDEX_CIRCLE_MENU;
        entity7.title = "圆环菜单";
        mDataList.add(entity7);

        UIEntity entity8 = new UIEntity();
        entity8.index = UIEntity.INDEX_GRID_RV;
        entity8.title = "双层RV";
        mDataList.add(entity8);

        UIEntity entity9 = new UIEntity();
        entity9.index = UIEntity.INDEX_CONSTRAINT_LAYOUT;
        entity9.title = "ConstraintLayout";
        mDataList.add(entity9);

        UIEntity entity10 = new UIEntity();
        entity10.index = UIEntity.INDEX_CONSTRAINT_GROUP;
        entity10.title = "ConstraintLayout--Group";
        mDataList.add(entity10);

        UIEntity entity11 = new UIEntity();
        entity11.index = UIEntity.INDEX_MOTION_LAYOUT;
        entity11.title = "MotionLayout";
        mDataList.add(entity11);

        UIEntity entity12 = new UIEntity();
        entity12.index = UIEntity.INDEX_TAB_LAYOUT_RV;
        entity12.title = "TabLayout 与 RV 联动";
        mDataList.add(entity12);

        UIEntity entity13 = new UIEntity();
        entity13.index = UIEntity.INDEX_LIFE_CYCLE_WINDOW;
        entity13.title = "Lifecycle Window";
        mDataList.add(entity13);

        UIEntity entity14 = new UIEntity();
        entity14.index = UIEntity.INDEX_GRADIENT_DRAWABLE;
        entity14.title = "Gradient Drawable";
        mDataList.add(entity14);

        UIEntity viewPager= new UIEntity();
        viewPager.index = UIEntity.INDEX_VIEW_PAGER;
        viewPager.title = "ViewPager（PageTransformer）";
        mDataList.add(viewPager);

        UIEntity entity15 = new UIEntity();
        entity15.index = UIEntity.INDEX_VIEW_PAGER_2;
        entity15.title = "ViewPager2";
        mDataList.add(entity15);

        UIEntity entity16= new UIEntity();
        entity16.index = UIEntity.INDEX_LAYOUT_MANAGER;
        entity16.title = "自定义LayoutManager";
        mDataList.add(entity16);

        UIEntity entity17= new UIEntity();
        entity17.index = UIEntity.INDEX_TOUCH_DELEGATE;
        entity17.title = "TouchDelegate(扩大点击区域)";
        mDataList.add(entity17);

        UIEntity rv = new UIEntity();
        rv.index = UIEntity.INDEX_RECYCLE_VIEW_SCROLLER;
        rv.title = "RecycleView滚动到指定位置";
        mDataList.add(rv);

        UIEntity sharedAnim = new UIEntity();
        sharedAnim.index = UIEntity.INDEX_SHARED_ANIM;
        sharedAnim.title = "共享元素动画";
        mDataList.add(sharedAnim);

        setDataList(mDataList);
    }

    @Override
    public UIAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_main_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(UIAdapter.ViewHolder holder, final int position) {
        final UIEntity entity = mDataList.get(position);
        holder.mTvTitle.setText(entity.title);

        holder.mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onClick(entity.index);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public void setDataList(ArrayList<UIEntity> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<UIEntity> dataList) {
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public void itemMoved(int fromPosition, int toPosition) {
        //在这里进行给原数组数据的移动
        Collections.swap(mDataList, fromPosition, toPosition);
        //通知数据移动
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void itemDismiss(int position) {
        //原数据移除数据
        mDataList.remove(position);
        //通知移除
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTvTitle;

        public ViewHolder(final View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
        }
    }

    public void setOnlClickListener(OnClickListener listener) {
        mClickListener = listener;
    }

    private OnClickListener mClickListener;

    public interface OnClickListener {

        void onClick(int index);
    }

}
