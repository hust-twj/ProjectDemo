package com.hust_twj.zademo.hook;

import android.util.Log;
import android.view.View;

import com.hust_twj.zademo.utils.LogUtils;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/3/31.
 */
public class HookProxyClickListener implements View.OnClickListener {

    private View.OnClickListener mListener;

    public HookProxyClickListener(View.OnClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onClick(View v) {
        LogUtils.e("twj124", "点击事件被hook到了");
        if (mListener != null) {
            mListener.onClick(v);
        }
    }

}
