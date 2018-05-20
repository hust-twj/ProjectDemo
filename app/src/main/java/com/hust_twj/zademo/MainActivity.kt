package com.hust_twj.zademo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hust_twj.zademo.moments_2_0.hot_topic.HotTopicActivity
import com.hust_twj.zademo.moments_2_0.hot_topic.PublishActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //动态2.0
        moment_2_0.setOnClickListener {
            startActivity(Intent(this, PublishActivity::class.java))
        }
    }
}
