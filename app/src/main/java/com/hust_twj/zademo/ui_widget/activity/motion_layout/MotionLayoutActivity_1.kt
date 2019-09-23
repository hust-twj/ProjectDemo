package com.hust_twj.zademo.ui_widget.activity.motion_layout

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.constraint.motion.MotionLayout
import android.util.Log

import com.hust_twj.zademo.R
import kotlinx.android.synthetic.main.activity_motion_layout.*
import kotlinx.android.synthetic.main.activity_motion_layout_3.*

/**
 * MotionLayout
 * Created by hust_twj
 * on 2019/9/22
 *
 */
class MotionLayoutActivity_1 : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_motion_layout_1)

        motion_layout.setTransitionListener(object : MotionLayout.TransitionListener {

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                Log.e("twj124", "onTransitionTrigger: " )
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                Log.e("twj124", "onTransitionStarted: " )
            }

            override fun onTransitionChange(p0: MotionLayout?, startId: Int, endId: Int, progress: Float) {
                Log.e("twj124", "onTransitionChange: ${progress}")
            }

            override fun onTransitionCompleted(p0: MotionLayout?, currentId: Int) {
                Log.e("twj124", "onTransitionCompleted: ${currentId}" )
            }

        })

    }

}
