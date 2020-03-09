package com.hust_twj.zademo.span;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.hust_twj.zademo.R;

public class SpanActivity extends Activity {

    private TextView mTvSpan;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_span);

        mTvSpan = findViewById(R.id.tv_span);
        //    <string name="three_link_mic_tips2">1.有人连麦时，守护可直接上麦，开始三人连麦~ \n
        // 2.守护连麦时，房间自动升级为三人连麦模式，主播可邀请第三个人上麦哦~</string>
        SpannableStringBuilder ssb = new SpannableStringBuilder("   有人连麦时，守护可直接上麦，开始三人连麦~ ");
        //ImageSpan span = new ImageSpan(getResources().getDrawable(R.drawable.icon_moment));
        ssb.setSpan(new ImageSpan(this, R.drawable.icon_moment, ImageSpan.ALIGN_BASELINE), 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        mTvSpan.setText(ssb);

    }
}
