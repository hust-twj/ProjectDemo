package com.hust_twj.zademo.queen_heart;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

public class QueenHeartActivity extends Activity {

    private TextView mTvQueenHeart;
    private TextView mTvTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_queen_heart);

        mTvQueenHeart = findViewById(R.id.tv_queen_heart);
        LogUtils.e("twj123", mTvQueenHeart.getWidth() + " -- " +  mTvQueenHeart.getHeight());
        mTvQueenHeart.setTypeface(null, Typeface.BOLD_ITALIC);
        /*LinearGradient linearGradient = new LinearGradient(0, 0, 0,  mTvQueenHeart.getPaint().getTextSize(),
                new int[]{R.color.colorPrimary, R.color.red}, null, Shader.TileMode.CLAMP);
        mTvQueenHeart.getPaint().setShader(linearGradient);*/

        mTvTest = findViewById(R.id.test_shadow3);
        mTvTest.setTypeface(null, Typeface.BOLD_ITALIC);
    }
}
