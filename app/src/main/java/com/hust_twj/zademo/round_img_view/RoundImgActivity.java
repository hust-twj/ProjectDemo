package com.hust_twj.zademo.round_img_view;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.RoundImageView;

public class RoundImgActivity extends Activity {

    private RoundImageView mIvLeft;
    private RoundImageView mIvCenter;
    private RoundImageView mIvRight;

    private ImageView mIvLeft2;
    private ImageView mIvCenter2;
    private ImageView mIvRight2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_round_img);

        mIvLeft = findViewById(R.id.left_live_img_view);
        mIvCenter = findViewById(R.id.center_live_img_view);
        mIvRight = findViewById(R.id.right_live_img_view);

        mIvLeft2 = findViewById(R.id.left_live_img_view_2);
        mIvCenter2 = findViewById(R.id.center_live_img_view_2);
        mIvRight2 = findViewById(R.id.right_live_img_view_2);

        mIvLeft.setImageResource(R.drawable.cover_default_home_live);
        mIvCenter.setImageResource(R.drawable.cover_default_home_live);
        mIvRight.setImageResource(R.drawable.cover_default_home_live);

        mIvLeft2.setImageResource(R.drawable.cover_default_home_live);
        mIvCenter2.setImageResource(R.drawable.cover_default_home_live);
        mIvRight2.setImageResource(R.drawable.cover_default_home_live);

    }
}
