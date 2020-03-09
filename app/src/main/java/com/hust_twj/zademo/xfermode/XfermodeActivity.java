package com.hust_twj.zademo.xfermode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.hust_twj.zademo.R;

public class XfermodeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_xfermode);

    }

    public void scratchCard(View view){
        startActivity(new Intent(this, ScratchCardActivity.class));

    }

}
