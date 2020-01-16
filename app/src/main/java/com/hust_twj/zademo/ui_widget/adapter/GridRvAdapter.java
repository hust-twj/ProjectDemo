package com.hust_twj.zademo.ui_widget.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.ui_widget.bean.GridMenu;
import com.hust_twj.zademo.ui_widget.widget.MatrixView;
import com.hust_twj.zademo.utils.DensityUtils;

import java.util.List;

/**
 * description ：
 * Created by Wenjing.Tang on 2019-05-24.
 */
public class GridRvAdapter extends RecyclerView.Adapter<GridRvAdapter.GridRvViewHolder> {

    private List<GridMenu> mData;
    private Context mContext;

    public GridRvAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<GridMenu> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void addData(List<GridMenu> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public GridRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.grid_menu_item, parent, false);
        return new GridRvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GridRvViewHolder holder, int position) {
        GridMenu bean = mData.get(position);
        holder.matrixView.setParentHeight(DensityUtils.dp2px(mContext, 200));
        holder.mIv.setBackground(mContext.getResources().getDrawable(bean.imgId));
        holder.mTv.setText(bean.label);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class GridRvViewHolder extends RecyclerView.ViewHolder {

        MatrixView matrixView;
        ImageView mIv;
        TextView mTv;

        public GridRvViewHolder(View itemView) {
            super(itemView);
            matrixView = itemView.findViewById(R.id.matrix_view);
            mIv = itemView.findViewById(R.id.id_circle_menu_item_image);
            mTv = itemView.findViewById(R.id.id_circle_menu_item_text);
        }
    }

}
