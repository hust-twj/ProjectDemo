package com.hust_twj.zademo.moment_2_0_optima;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.moments_2_0.hot_topic.widget.ContentIntroductionLayout;

public class SpannableActivity extends Activity {

    private ContentIntroductionLayout contentIntroductionLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_spannable);

        contentIntroductionLayout = findViewById(R.id.layout_content_introduction);
        String content = "你好啊你好啊你好啊你好啊1" + "你好啊你好啊你好啊你好啊2"
                 + "你好啊你好啊你好啊你好啊3" + "你好啊你好啊你好啊你好啊4"
                + "你好啊你好啊你好啊你好啊5" +  "你好啊你好啊你好啊你好啊6"
               + "你好啊你好啊你好啊你好啊7" +  "你好啊你好啊你好啊你好啊8";
        contentIntroductionLayout.updateView(content);
    }
}
