package com.hust_twj.zademo.kotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.hust_twj.zademo.R
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlinx.coroutines.*

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-08-07.
 */
class KotlinActivity : AppCompatActivity() {

    val TAG = KotlinActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        Log.e(TAG, "onCreate: " + System.currentTimeMillis())


        tv_coroutine.setOnClickListener {
            startActivity(Intent(this@KotlinActivity, CoroutineActivity::class.java))
        }
    }

}