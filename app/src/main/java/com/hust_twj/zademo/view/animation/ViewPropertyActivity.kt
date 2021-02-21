package com.hust_twj.zademo.view.animation

import android.app.Activity
import android.os.Bundle
import com.hust_twj.zademo.R
import kotlinx.android.synthetic.main.activity_animation_property.*

/**
 * 属性动画
 * Created by hust_twj
 * on 2021/2/21
 */
class ViewPropertyActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_property)

        /**
         * 属性动画：ViewPropertyAnimator
         */
        view.setOnClickListener {
            it.animate().translationX(100f).alpha(0.5f).setDuration(1000)
            //.setInterpolator()
            //...
            // .start()
        }

    }
}