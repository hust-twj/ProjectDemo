package com.hust_twj.zademo.third_part.data_binding;

import android.app.Activity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.databinding.ActivityDataBindingBinding;

public class DataBindingActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        User user = new User("twj", 18);
        binding.setUser(user);
    }

}
