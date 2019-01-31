package com.hust_twj.zademo.share;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hust_twj.zademo.R;

/**
 * Created by Wenjing.Tang
 * on 2019/1/31
 */
public class ShareActivity extends Activity {

    private TextView mTvShare;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        mTvShare = findViewById(R.id.tv_share);

        mTvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveShareDialog shareDialog = new LiveShareDialog(ShareActivity.this);
                String imageUrl = "https://b-ssl.duitang.com/uploads/item/201303/15/20130315223944_EvRW3.thumb.1400_0.jpeg";
                String shareUrl = "https://www.baidu.com";
                shareDialog.setShareContent("分享" , "你知道我在等你吗", imageUrl, shareUrl);
                shareDialog.popup();

            }
        });
    }
}
