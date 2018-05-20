package com.hust_twj.zademo.moments_2_0;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hust_twj.zademo.R;

import java.util.ArrayList;

/**
 *
 * Created by hust_twj on 2018/5/19.
 */

public class HotTopicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<HotTopicEntity> mDataList = new ArrayList<>();

    public HotTopicAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(ArrayList<HotTopicEntity> dataList){
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.moments_hot_topic_item, parent, false);
        return new HotTopicHolder(view) ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final HotTopicEntity entity = mDataList.get(position);
        //((HotTopicHolder)holder).mIvTopic.setBackground();
        ((HotTopicHolder)holder).mTvTopicTitle.setText(entity.topicTitle);
        ((HotTopicHolder)holder).mTvTopicDesc.setText(entity.topicDesc);
        ((HotTopicHolder)holder).mViewParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClicked(entity.topicTitle, entity.topicID);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size() ;
    }

    class HotTopicHolder extends RecyclerView.ViewHolder{

        View mViewParent;
        ImageView mIvTopic;
        TextView mTvTopicTitle;
        TextView mTvTopicDesc;

        HotTopicHolder(View itemView) {
            super(itemView);
            mViewParent = itemView.findViewById(R.id.layout_patent);
            mIvTopic = itemView.findViewById(R.id.iv_hot_topic);
            mTvTopicTitle = itemView.findViewById(R.id.tv_hot_topic_title);
            mTvTopicDesc = itemView.findViewById(R.id.tv_hot_topic_desc);
        }
    }

    public interface OnItemClickListener{
        void onItemClicked(String topicTitle, int topicID);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

}
