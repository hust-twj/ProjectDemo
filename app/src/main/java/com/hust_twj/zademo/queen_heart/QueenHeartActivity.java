package com.hust_twj.zademo.queen_heart;

import android.app.Activity;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.hust_twj.zademo.R;

public class QueenHeartActivity extends Activity {

    private TextView mTvQueenHeart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_queen_heart);

        mTvQueenHeart = findViewById(R.id.tv_queen_heart);
        mTvQueenHeart.setTypeface(null, Typeface.BOLD_ITALIC);
        LinearGradient linearGradient = new LinearGradient(0, 0, 0,  mTvQueenHeart.getPaint().getTextSize(),
                new int[]{R.color.color_4d4d4d,R.color.red}, null, Shader.TileMode.CLAMP);
        mTvQueenHeart.getPaint().setShader(linearGradient);
    }
}
