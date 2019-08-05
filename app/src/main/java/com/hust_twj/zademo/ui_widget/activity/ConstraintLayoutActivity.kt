package com.hust_twj.zademo.ui_widget.activity

import android.app.Activity
import android.os.Bundle
import android.view.View

import com.hust_twj.zademo.R
import kotlinx.android.synthetic.main.activity_constraint_layout.*

/**
 * Created by hust_twj
 * on 2019/2/26
 *
 */
class ConstraintLayoutActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_constraint_layout)

        btn_switch_visibility.setOnClickListener {
            if (group.visibility == View.VISIBLE) {
                group.visibility = View.GONE
            }else {
                group.visibility = View.VISIBLE
            }

        }

    }

}
