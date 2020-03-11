package com.hust_twj.zademo.ui_widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.main.IItemHelper;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Wenjing.Tang
 * on 2019/1/8
 */
public class RvScrollerAdapter extends RecyclerView.Adapter<RvScrollerAdapter.ViewHolder> implements IItemHelper {

    private Context mContext;
    private ArrayList<Integer> mDataList = new ArrayList<>();

    public RvScrollerAdapter(Context context) {
        mContext = context;

        initData();
    }

    private void initData() {

        for (int i = 0; i < 35; i++) {
            mDataList.add(i);
        }
        setDataList(mDataList);
    }

    @NotNull
    @Override
    public RvScrollerAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_main_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(RvScrollerAdapter.ViewHolder holder, final int position) {
        final int pos = mDataList.get(position);
        holder.mTvTitle.setText(String.valueOf(pos));
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public void setDataList(ArrayList<Integer> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<Integer> dataList) {
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
