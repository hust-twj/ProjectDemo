package com.hust_twj.zademo.ui_widget.activity.anim;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.main.IItemHelper;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Wenjing.Tang
 * on 2019/1/8
 */
public class SharedAnimAdapter extends RecyclerView.Adapter<SharedAnimAdapter.ViewHolder> implements IItemHelper {

    private Activity mContext;
    private ArrayList<SharedAnimEntity> mDataList;

    public SharedAnimAdapter(Activity context) {
        mContext = context;

        initData();
    }

    private void initData() {
        ArrayList<SharedAnimEntity> mDataList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            SharedAnimEntity rv = new SharedAnimEntity();
            if (i % 2 == 0) {
                rv.bg = R.drawable.icon_douyin;
                rv.title = "抖音";
                rv.desc = "抖音  记录美好生活";
            } else {
                rv.bg = R.drawable.icon_facebook;
                rv.title = "facebook";
                rv.desc = "hahahaha";
            }
            mDataList.add(rv);
        }

        setDataList(mDataList);
    }

    @Override
    public SharedAnimAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_shared_anim_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(SharedAnimAdapter.ViewHolder holder, final int position) {
        final SharedAnimEntity entity = mDataList.get(position);
        holder.mIvBg.setBackgroundResource(entity.bg);
        holder.mTvTitle.setText(entity.title);
        holder.mTvDes.setText(entity.desc);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(mContext, holder.mIvBg, "shareElement").toBundle();

                Intent intent = new Intent(mContext, SharedAnimDetailActivity.class);
                intent.putExtra("shared_entity", entity);
                mContext.startActivity(intent, bundle);

                if (mClickListener != null) {
                    mClickListener.onClick(entity);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public void setDataList(ArrayList<SharedAnimEntity> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<SharedAnimEntity> dataList) {
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
        ImageView mIvBg;
        TextView mTvTitle;
        TextView mTvDes;

        public ViewHolder(final View itemView) {
            super(itemView);
            mIvBg = itemView.findViewById(R.id.iv_icon);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mTvTitle.getPaint().setFakeBoldText(true);
            mTvDes = itemView.findViewById(R.id.tv_des);
        }
    }

    public void setOnlClickListener(OnClickListener listener) {
        mClickListener = listener;
    }

    private OnClickListener mClickListener;

    public interface OnClickListener {

        void onClick(SharedAnimEntity index);
    }

}
