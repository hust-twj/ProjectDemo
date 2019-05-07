package com.hust_twj.zademo.leak_canary

import android.app.Activity
import android.os.Bundle

import com.hust_twj.zademo.R
import kotlinx.android.synthetic.main.activity_leak_canary.*

/**
 * Created by Wenjing.Tang
 * on 2019/5/1
 */
class LeakCanaryActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_leak_canary)

        tv_handler.setOnClickListener {

        }

        tv_non_static.setOnClickListener {

        }

    }

}
