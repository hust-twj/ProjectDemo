package com.hust_twj.zademo.download;

/**
 * Description ：下载监听
 * Created by Wenjing.Tang on 2020/4/15.
 */
public interface DownloadListener {

    void onFinished();

    void onProgress(float progress);

    void onPause();

    void onCancel();

}
