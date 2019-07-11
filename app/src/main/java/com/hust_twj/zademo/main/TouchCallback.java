package com.hust_twj.zademo.main;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * @author hust_twj
 * @date 2019/7/11
 */
public class TouchCallback extends ItemTouchHelper.Callback {
    private IItemHelper itemHelper;

    public TouchCallback(IItemHelper itemHelper){
        this.itemHelper = itemHelper;
    }

    //声明不同转台下的移动方向
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        int swipedFlags = ItemTouchHelper.START|ItemTouchHelper.END;
        return makeMovementFlags(dragFlags,swipedFlags);
    }

    // 拖动的条目从旧位置--到新位置时调用
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        itemHelper.itemMoved(viewHolder.getLayoutPosition(),target.getLayoutPosition());
        return false;
    }

    // 滑动到消失调用
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        itemHelper.itemDismiss(viewHolder.getLayoutPosition());
    }

    /**
     * true --开启长按
     * false --关闭长按拖动 默认开启
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    //状态改变时调用 比如正在滑动，正在拖动
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        //不是空闲状态--背景加深
        if(actionState!= ItemTouchHelper.ACTION_STATE_IDLE){
            viewHolder.itemView.setBackgroundColor(Color.GRAY);
        }
    }

    //滑动完，拖动完调用
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(0);
    }

}
