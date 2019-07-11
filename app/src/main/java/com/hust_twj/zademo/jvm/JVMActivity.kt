package com.hust_twj.zademo.jvm

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import com.hust_twj.zademo.R
import com.hust_twj.zademo.jvm.invoke.InvokeActivity
import kotlinx.android.synthetic.main.activity_jvm.*

/**
 * Created by Wenjing.Tang
 * on 2019/3/30
 */
class JVMActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_jvm)

        tv_activity_rq.setOnClickListener {
            startActivity(Intent(this@JVMActivity, ReferenceQueueActivity::class.java))
        }

        tv_invoke.setOnClickListener {
            startActivity(Intent(this@JVMActivity, InvokeActivity::class.java))
        }

    }

}
