package com.hust_twj.zademo.ui_widget.activity.motion_layout

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import com.hust_twj.zademo.R
import kotlinx.android.synthetic.main.activity_motion_layout.*

/**
 * MotionLayout
 * Created by hust_twj
 * on 2019/9/22
 *
 */
class MotionLayoutActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_motion_layout)


        tv_motion_layout_1.setOnClickListener {
            startActivity(Intent(this@MotionLayoutActivity, MotionLayoutActivity_1::class.java))
        }

        tv_motion_layout_2.setOnClickListener {
            startActivity(Intent(this@MotionLayoutActivity, MotionLayoutActivity_2::class.java))
        }

        tv_motion_layout_3.setOnClickListener {
            startActivity(Intent(this@MotionLayoutActivity, MotionLayoutActivity_3::class.java))
        }

        tv_motion_layout_4.setOnClickListener {
            startActivity(Intent(this@MotionLayoutActivity, MotionLayoutActivity_4::class.java))
        }

        tv_motion_layout_5.setOnClickListener {
            startActivity(Intent(this@MotionLayoutActivity, MotionLayoutActivity_5::class.java))
        }
    }

}
