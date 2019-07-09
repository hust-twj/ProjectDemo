package com.hust_twj.zademo.third_part

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.hust_twj.zademo.R
import com.hust_twj.zademo.butter_knife.ButterKnifeActivity
import com.hust_twj.zademo.third_part.event_bus.EventBusDemoActivity
import com.hust_twj.zademo.third_part.leak_canary.LeakCanaryActivity
import com.hust_twj.zademo.third_part.rx_java.RxJavaActivity
import kotlinx.android.synthetic.main.activity_third_part.*


/**
 * 三方开源库学习
 * Created by Wenjing.Tang on 2019-07-09.
 */
class ThirdPartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_third_part)

        tv_event_bus.setOnClickListener {
            startActivity(Intent(this@ThirdPartActivity, EventBusDemoActivity::class.java))
        }

        tv_butter_knife.setOnClickListener {
            startActivity(Intent(this@ThirdPartActivity, ButterKnifeActivity::class.java))
        }

        tv_leak_canary.setOnClickListener {
            startActivity(Intent(this@ThirdPartActivity, LeakCanaryActivity::class.java))
        }

        tv_rx_java.setOnClickListener {
            startActivity(Intent(this@ThirdPartActivity, RxJavaActivity::class.java))
        }
    }

}
