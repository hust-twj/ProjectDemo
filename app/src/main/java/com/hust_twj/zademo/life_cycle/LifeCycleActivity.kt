package com.hust_twj.zademo.life_cycle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hust_twj.zademo.R
import kotlinx.android.synthetic.main.activity_lifecycle_main.*


/**
 *
 * Created by Wenjing.Tang on 2019-07-09.
 */
class LifeCycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_lifecycle_main)

        tv_activity_life_cycle.setOnClickListener {
            startActivity(Intent(this@LifeCycleActivity, ActivityLifeCycleActivity::class.java))
        }

        tv_service_life_cycle.setOnClickListener {
            startActivity(Intent(this@LifeCycleActivity, ServiceLifeCycleActivity::class.java))
        }

    }

}
