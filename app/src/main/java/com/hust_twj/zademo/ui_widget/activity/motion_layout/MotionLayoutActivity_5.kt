package com.hust_twj.zademo.ui_widget.activity.motion_layout

import android.app.Activity
import android.os.Bundle
import android.support.constraint.motion.MotionLayout
import android.support.design.widget.AppBarLayout

import com.hust_twj.zademo.R
import kotlinx.android.synthetic.main.activity_motion_layout_5.*

/**
 * MotionLayout
 * Created by hust_twj
 * on 2019/9/22
 *
 */
class MotionLayoutActivity_5 : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_motion_layout_5)

        val listener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val seekPosition = -verticalOffset / appbar_layout.totalScrollRange.toFloat()
            motion_layout.progress = seekPosition
        }

        appbar_layout.addOnOffsetChangedListener(listener)

    }

}
