package com.hust_twj.zademo.ui_widget.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.bean.CountDownBean;

import java.util.List;

/**
 * description ：
 * Created by Wenjing.Tang on 2019-05-24.
 */
public class CountDownAdapter extends RecyclerView.Adapter<CountDownAdapter.CountDownViewHolder> {

    private List<CountDownBean> mData;

    public void setData(List<CountDownBean> data){
        mData = data;
        notifyDataSetChanged();
    }

    public void addData(List<CountDownBean> data){
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public CountDownViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_count_down, null, false);
        return new CountDownViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountDownViewHolder holder, int position) {
        CountDownBean bean = mData.get(position);
        holder.mTvTotalTime.setText("总时间："+bean.tiotalTime);
        holder.mTvRemainTime.setText("剩余时间：" /*+ bean.tiotalTime*/);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class CountDownViewHolder extends RecyclerView.ViewHolder {

        TextView mTvTotalTime;
        TextView mTvRemainTime;

        public CountDownViewHolder(View itemView) {
            super(itemView);
            mTvTotalTime = itemView.findViewById(R.id.tv_total_time);
            mTvRemainTime = itemView.findViewById(R.id.tv_remain_time);
        }
    }

}
