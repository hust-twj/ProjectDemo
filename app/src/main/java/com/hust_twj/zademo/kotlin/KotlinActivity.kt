package com.hust_twj.zademo.kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.hust_twj.zademo.R
import com.hust_twj.zademo.kotlin.collection.CollectionJavaActivity
import com.hust_twj.zademo.kotlin.collection.CollectionktActivity
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


        tv_collection_java.setOnClickListener {
            startActivity(Intent(this@KotlinActivity, CollectionJavaActivity::class.java))
        }

        tv_collection_kt.setOnClickListener {
            startActivity(Intent(this@KotlinActivity, CollectionktActivity::class.java))
        }
    }

}