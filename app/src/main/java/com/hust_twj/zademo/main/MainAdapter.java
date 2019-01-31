package com.hust_twj.zademo.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.hust_twj.zademo.R;

import java.util.ArrayList;

/**
 * Created by Wenjing.Tang
 * on 2019/1/8
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<MainEntity> mDataList;

    public MainAdapter(Context context) {
        mContext = context;

        initData();
    }

    private void initData() {
        ArrayList<MainEntity> mDataList = new ArrayList<>();

        MainEntity entity0 = new MainEntity();
        entity0.index = MainEntity.INDEX_PUBLISH;
        entity0.title = "动态2.0 选择话题";
        mDataList.add(entity0);

        MainEntity entity1 = new MainEntity();
        entity1.index = MainEntity.INDEX_SPANNABLE;
        entity1.title = "动态2.0优化";
        mDataList.add(entity1);

        MainEntity entity2 = new MainEntity();
        entity2.index = MainEntity.INDEX_MOMENT_DETAIL;
        entity2.title = "动态2.0-话题详情";
        mDataList.add(entity2);

        MainEntity entity3 = new MainEntity();
        entity3.index = MainEntity.INDEX_PIC_TEXT;
        entity3.title = "图文混排";
        mDataList.add(entity3);

        MainEntity entity4 = new MainEntity();
        entity4.index = MainEntity.INDEX_LIST;
        entity4.title = "list";
        mDataList.add(entity4);

        MainEntity entity5 = new MainEntity();
        entity5.index = MainEntity.INDEX_QUEEN_HEART;
        entity5.title = "女王之心";
        mDataList.add(entity5);

        MainEntity entity6 = new MainEntity();
        entity6.index = MainEntity.INDEX_LIVE_END;
        entity6.title = "直播结束";
        mDataList.add(entity6);

        MainEntity entity7 = new MainEntity();
        entity7.index = MainEntity.INDEX_SPAN;
        entity7.title = "Span图文混排";
        mDataList.add(entity7);

        MainEntity entity8 = new MainEntity();
        entity8.index = MainEntity.INDEX_TOAST;
        entity8.title = "toast显示";
        mDataList.add(entity8);

        MainEntity entity9 = new MainEntity();
        entity9.index = MainEntity.INDEX_FRAME;
        entity9.title = "圆角FrameLayout";
        mDataList.add(entity9);

        MainEntity entity10 = new MainEntity();
        entity10.index = MainEntity.INDEX_LINE_SPACE;
        entity10.title = "LineSpacingExtra";
        mDataList.add(entity10);

        MainEntity entity11 = new MainEntity();
        entity11.index = MainEntity.INDEX_ROUND_IMAGE;
        entity11.title = "RoundImgView";
        mDataList.add(entity11);

        MainEntity entity12 = new MainEntity();
        entity12.index = MainEntity.INDEX_EVENT_BUS;
        entity12.title = "Event Bus";
        mDataList.add(entity12);

        MainEntity entity13 = new MainEntity();
        entity13.index = MainEntity.INDEX_HANDLER;
        entity13.title = "handler";
        mDataList.add(entity13);

        MainEntity entity14 = new MainEntity();
        entity14.index = MainEntity.INDEX_BOTTOM_SHEET;
        entity14.title = "BottomSheet";
        mDataList.add(entity14);

        MainEntity entity15 = new MainEntity();
        entity15.index = MainEntity.INDEX_SHARE;
        entity15.title = "分享";
        mDataList.add(entity15);

        MainEntity entity16 = new MainEntity();
        entity16.index = MainEntity.INDEX_PAY;
        entity16.title = "支付";
        mDataList.add(entity16);

        setDataList(mDataList);
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_main_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, final int position) {
        final MainEntity entity = mDataList.get(position);
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


    public void setDataList(ArrayList<MainEntity> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<MainEntity> dataList) {
        mDataList.addAll(dataList);
        notifyDataSetChanged();
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
