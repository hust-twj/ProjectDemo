package com.hust_twj.zademo.bitmap_opti;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.xfermode.ScratchCardActivity;

import java.io.IOException;
import java.io.InputStream;

public class BitmapOriginalActivity extends Activity {

    private ImageView mIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bitmap_original);

        mIv = findViewById(R.id.iv_image);

    }

    public void loadImage(View view){
        try {
            //加载原图，消耗内存37M-->320M-->230M
            //159--> 278
            InputStream inputStream = getAssets().open("world_map.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            mIv.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
