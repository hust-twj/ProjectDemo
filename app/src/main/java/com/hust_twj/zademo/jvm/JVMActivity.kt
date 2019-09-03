package com.hust_twj.zademo.jvm

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle

import com.hust_twj.zademo.R
import com.hust_twj.zademo.jvm.invoke.InvokeActivity
import com.hust_twj.zademo.utils.LogUtils
import kotlinx.android.synthetic.main.activity_jvm.*

/**
 * Created by Wenjing.Tang
 * on 2019/3/30
 */
class JVMActivity : Activity() {

    private lateinit var manager: ActivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_jvm)

        tv_activity_rq.setOnClickListener {
            startActivity(Intent(this@JVMActivity, ReferenceQueueActivity::class.java))
        }

        tv_invoke.setOnClickListener {
            startActivity(Intent(this@JVMActivity, InvokeActivity::class.java))
        }

        manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val heapSize = manager.memoryClass
        //384
        LogUtils.e("twj", heapSize)

    }

}
