package com.hust_twj.zademo.view.animation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.hust_twj.zademo.R
import kotlinx.android.synthetic.main.activity_animation.*

/**
 * Created by hust_twj
 * on 2021/2/21
 */
class ViewAnimationActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        property_animation.setOnClickListener {
            startActivity(Intent(this@ViewAnimationActivity, ViewPropertyActivity::class.java))
        }
    }
}