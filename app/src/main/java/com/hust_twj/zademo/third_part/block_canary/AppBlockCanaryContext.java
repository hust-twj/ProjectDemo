package com.hust_twj.zademo.third_part.block_canary;

import com.github.moduth.blockcanary.BlockCanaryContext;

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/4/18.
 */
public class AppBlockCanaryContext extends BlockCanaryContext {


    @Override
    public int getConfigBlockThreshold() {
        return 5000;
    }

    // if set true, notification will be shown, else only write log file
    @Override
    public boolean isNeedDisplay() {
        return true;
    }

    // path to save log file
    @Override
    public String getLogPath() {
        return "/mnt/sdcard/";
    }

}
