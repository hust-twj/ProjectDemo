package com.hust_twj.zademo.third_part.leak_canary

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import com.hust_twj.zademo.R
import kotlinx.android.synthetic.main.activity_leak_canary.*

/**
 * Created by Wenjing.Tang
 * on 2019/8/1
 */
class LeakCanaryActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_leak_canary)

        tv_singleton.setOnClickListener {
            startActivity(Intent(this@LeakCanaryActivity, SingletonLeakActivity::class.java))
        }

        tv_handler_leak.setOnClickListener {
            startActivity(Intent(this@LeakCanaryActivity, HandlerLeakActivity::class.java))
        }

        tv_non_static_leak.setOnClickListener {
            startActivity(Intent(this@LeakCanaryActivity, NonStaticLeakActivity::class.java))
        }

    }

}
