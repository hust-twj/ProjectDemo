package com.hust_twj.zademo.third_part.retrofit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.hust_twj.zademo.R
import com.hust_twj.zademo.third_part.retrofit.annotation.AnnoationTestActivity
import com.hust_twj.zademo.third_part.retrofit.proxy.ProxyActivity
import kotlinx.android.synthetic.main.activity_about_retrofit.*

/**
 * Description ：
 * Created by Wenjing.Tang on 2020-01-06.
 */
class AboutRetrofitActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_retrofit)

        tv_annotation.setOnClickListener {
            startActivity(Intent(this@AboutRetrofitActivity, AnnoationTestActivity::class.java))
        }

        tv_retrofit.setOnClickListener {
            startActivity(Intent(this@AboutRetrofitActivity, RetrofitActivity::class.java))
        }

        tv_proxy.setOnClickListener {
            startActivity(Intent(this@AboutRetrofitActivity, ProxyActivity::class.java))
        }
    }
}