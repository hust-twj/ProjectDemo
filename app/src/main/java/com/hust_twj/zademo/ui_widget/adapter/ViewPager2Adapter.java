package com.hust_twj.zademo.ui_widget.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hust_twj.zademo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description ：ViewPager2 的 Adapter
 * Created by Wenjing.Tang on 2020-02-16.
 */
public class ViewPager2Adapter extends RecyclerView.Adapter<ViewPager2Adapter.PagerViewHolder> {

    private List<Integer> mList = new ArrayList();

    public void setList(List<Integer> list) {
        mList = list;
    }

    @NonNull
    @Override
    public PagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page, parent, false);
        return new PagerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PagerViewHolder holder, int position) {

        holder.bindData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class PagerViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        private String[] colors = {"#CCFF99", "#41F1E5", "#8D41F1", "#FF99CC"};

        public PagerViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_text);
        }

        public void bindData(int i) {
            mTextView.setText(String.valueOf(i));
            mTextView.setBackgroundColor(Color.parseColor(colors[i]));
        }
    }

}
