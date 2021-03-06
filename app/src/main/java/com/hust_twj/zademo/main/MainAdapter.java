package com.hust_twj.zademo.main;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hust_twj.zademo.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Wenjing.Tang
 * on 2019/1/8
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> implements IItemHelper {

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
        entity12.index = MainEntity.INDEX_THIRD_PART;
        entity12.title = "第三方开源框架";
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

        MainEntity entity17 = new MainEntity();
        entity17.index = MainEntity.INDEX_REMOTE_VIEW;
        entity17.title = "RemoteView";
        mDataList.add(entity17);

        MainEntity entity18 = new MainEntity();
        entity18.index = MainEntity.INDEX_XFERMODE;
        entity18.title = "Xfermode";
        mDataList.add(entity18);

        MainEntity entity19 = new MainEntity();
        entity19.index = MainEntity.INDEX_BITMAP;
        entity19.title = "Bitmap加载";
        mDataList.add(entity19);

        MainEntity entity20 = new MainEntity();
        entity20.index = MainEntity.INDEX_UI_WIDGET;
        entity20.title = "UI控件";
        mDataList.add(entity20);

        MainEntity entity21 = new MainEntity();
        entity21.index = MainEntity.INDEX_AIDL;
        entity21.title = "AIDL";
        mDataList.add(entity21);

        MainEntity entity22 = new MainEntity();
        entity22.index = MainEntity.INDEX_VIEW;
        entity22.title = "VIEW相关（事件分发等）";
        mDataList.add(entity22);

        MainEntity entity23 = new MainEntity();
        entity23.index = MainEntity.INDEX_UI_PARAMS;
        entity23.title = "UI参数";
        mDataList.add(entity23);

        MainEntity entity24 = new MainEntity();
        entity24.index = MainEntity.INDEX_DOWNLOAD;
        entity24.title = "下载 断点续传 ";
        mDataList.add(entity24);

        MainEntity entity25 = new MainEntity();
        entity25.index = MainEntity.INDEX_BLOCK_QUEUE;
        entity25.title = "BlockQueue（生产者/消费者模式）";
        mDataList.add(entity25);

        MainEntity entity26 = new MainEntity();
        entity26.index = MainEntity.INDEX_THREAD;
        entity26.title = "多线程 并发";
        mDataList.add(entity26);

        MainEntity entity27 = new MainEntity();
        entity27.index = MainEntity.INDEX_JVM;
        entity27.title = "JVM";
        mDataList.add(entity27);

        MainEntity entity28 = new MainEntity();
        entity28.index = MainEntity.INDEX_KOTLIN;
        entity28.title = "Kotin";
        mDataList.add(entity28);

        MainEntity entity29 = new MainEntity();
        entity29.index = MainEntity.INDEX_ARCH;
        entity29.title = "JetPack";
        mDataList.add(entity29);

        MainEntity entity30 = new MainEntity();
        entity30.index = MainEntity.INDEX_LIFE_CYCLE;
        entity30.title = "四大组件（生命周期）";
        mDataList.add(entity30);

        MainEntity entity31 = new MainEntity();
        entity31.index = MainEntity.INDEX_HOOK;
        entity31.title = "hook";
        mDataList.add(entity31);

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
