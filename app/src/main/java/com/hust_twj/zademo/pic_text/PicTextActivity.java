package com.hust_twj.zademo.pic_text;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.widget.AutoFitTextView;
import com.hust_twj.zademo.widget.PicTextView;

public class PicTextActivity extends Activity {

    private AutoFitTextView autoFitTextView;

    private PicTextView picTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_text);

        autoFitTextView = findViewById(R.id.auto_fit_text_view);
        autoFitTextView.setContent("我擦，明明只设置了上面两个圆角的值，下面两个角应TextView该还是直角才对，结果下面两个也变成了圆角了，并且只有修改左上角的值才有效果。");


        picTextView = findViewById(R.id.pic_text_view);
        picTextView.setContent("最近在做一个类似于QQ空间的一个社交圈的模块的开发。有一个需求是当用户发表的内容超出4行时，显示一个按钮，点击按钮展示全文。我还真没有发现TextView有获取");

       /* picTextView.setType(PicTextView.TYPE_TEXT);
        picTextView.setIconText("最近在做一个类似于QQ空间的一个社交圈的模块的开发。");*/
    }

}
