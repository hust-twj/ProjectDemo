package com.hust_twj.zademo.JetPack

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.hust_twj.zademo.R
import com.hust_twj.zademo.JetPack.life_cycle.LifeCycleActivity
import com.hust_twj.zademo.JetPack.live_data.LiveDataActivity
import com.hust_twj.zademo.JetPack.live_data.UserViewModel
import kotlinx.android.synthetic.main.activity_acrh.*

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-08-07.
 */
class JetPackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acrh)

        tvLifeCycle.setOnClickListener {
            startActivity(Intent(this@JetPackActivity, LifeCycleActivity::class.java))
        }

        tvLiveData.setOnClickListener {
            startActivity(Intent(this@JetPackActivity, LiveDataActivity::class.java))
        }

        tv_info.text = "11"

        subscribe()

    }

    private fun subscribe() {
        val userViewModel = UserViewModel.getInstance()
        userViewModel.user.observe(this, Observer {
            if (it == null) {
                return@Observer
            }
            Log.e("twj124", "acrh onchange: "+ it.toString())
            tv_info.text = it.toString()
        })
    }

}