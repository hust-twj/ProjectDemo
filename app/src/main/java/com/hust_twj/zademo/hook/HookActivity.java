package com.hust_twj.zademo.hook;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-11-19.
 */
public class HookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);

        TextView textView = findViewById(R.id.tv_hook);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("twj124","onClick");
            }
        });

        try {
            HookSetOnClickListenerHelper.hook(this, textView);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

}
