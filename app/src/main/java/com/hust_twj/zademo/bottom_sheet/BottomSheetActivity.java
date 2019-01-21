package com.hust_twj.zademo.bottom_sheet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;

import com.hust_twj.zademo.R;


public class BottomSheetActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bottom_sheet);

        findViewById(R.id.tv_bottom_sheet_dialog).setOnClickListener(this);
        findViewById(R.id.tv_bottom_sheet_fragment).setOnClickListener(this);
        findViewById(R.id.tv_bottom_sheet_behavior).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bottom_sheet_dialog:
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
                bottomSheetDialog.setContentView(R.layout.layout_bottom_sheet_dialog);
                bottomSheetDialog.show();
                break;
            case R.id.tv_bottom_sheet_behavior:
                startActivity(new Intent(BottomSheetActivity.this, BottomSheetBehaviorActivity.class));
                break;
            case R.id.tv_bottom_sheet_fragment:
                break;
                default:
                    break;
        }

    }
}
