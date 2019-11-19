package com.hust_twj.zademo.ui_widget.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-11-17.
 */
public class MyHandler extends Handler {

    private WeakReference<Activity> reference;
    public MyHandler(Activity activity) {
        reference = new WeakReference<Activity>(activity);
    }
    @Override
    public void handleMessage(Message msg) {
//        if (reference.get() != null) {
//            switch (msg.what) {
//                case 0:
//                    //停止位置为【商品】
//                    if (scrollHeight <= topBannerAndInfoHeight) {
//                        mBinding.magicIndicator.onPageSelected(0);
//                        //停止位置为【详情】
//                    } else if (scrollHeight > topBannerAndInfoHeight && scrollHeight <= topGoodsPicHeight) {
//                        mBinding.magicIndicator.onPageSelected(1);
//                        //停止位置为【喜欢】
//                    } else {
//                        mBinding.magicIndicator.onPageSelected(2);
//                    }
//                    break;
//                case 1://如果是点击的tab,不重新选择选项卡
//                    isClickTab=false;
//                    break;
//            }
//        }
    }
}
