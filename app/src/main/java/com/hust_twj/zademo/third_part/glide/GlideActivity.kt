package com.hust_twj.zademo.third_part.glide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.hust_twj.zademo.R
import kotlinx.android.synthetic.main.activity_glide.*

/**
 * Glide使用：
 */
class GlideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

        Glide.with(this).load("").into(iv)

    }
}