package com.hust_twj.zademo.leak_canary;

import android.content.Context;

/**
 * description ：
 * Created by Wenjing.Tang on 2019-05-22.
 */
public class CommonUtils {

    private static CommonUtils mInstance;

    private Context mContext;

    private CommonUtils(Context context) {
        this.mContext = context;
    }

    public static CommonUtils getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new CommonUtils(context);
        }
        return mInstance;
    }

}
