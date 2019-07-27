package com.hust_twj.zademo.handler

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import com.hust_twj.zademo.R
import kotlinx.android.synthetic.main.activity_main_handler.*

/**
 * @author hust_twj
 * @date 2019/7/27
 */
class MainHandlerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_handler)

        tv_handler.setOnClickListener {
            startActivity(Intent(this@MainHandlerActivity, HandlerActivity::class.java))
        }

        tv_handler_thread.setOnClickListener {
            startActivity(Intent(this@MainHandlerActivity, HandlerThreadActivity::class.java))
        }

        tv_idle_handler.setOnClickListener {
            startActivity(Intent(this@MainHandlerActivity, IdlerHandlerActivity::class.java))
        }
    }
}
