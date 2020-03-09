package com.hust_twj.zademo.moment_2_0_optima;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.moments_2_0.hot_topic.widget.ContentIntroductionLayout;
import com.hust_twj.zademo.widget.CenterAlignedImageSpan;

public class SpannableActivity extends Activity {

    private ContentIntroductionLayout contentIntroductionLayout;
    private TextView mTv;
    private SpannableString sSIcon;
    private static final String SS_LAB = "#&&#";

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

        mTv = findViewById(R.id.tv_span);
        final SpannableStringBuilder spannableString;

        spannableString = new SpannableStringBuilder("冒泡的茄子" + " " + "送" + " " + "生气的西米露" + " " + "冰淇淋");
        final int length = spannableString.length();

        sSIcon = new SpannableString(SS_LAB);
        sSIcon.setSpan(new CenterAlignedImageSpan(this, R.drawable.heart_word_icon),
                0, sSIcon.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        mTv.setText(spannableString.toString());
        mTv.append(" ");
        mTv.append(sSIcon);
        mTv.append(" x1 ");

    }
}
