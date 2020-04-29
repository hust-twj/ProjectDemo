package com.hust_twj.zademo.jet_pack

import androidx.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.hust_twj.zademo.R
import com.hust_twj.zademo.jet_pack.life_cycle.LifeCycleActivity
import com.hust_twj.zademo.jet_pack.live_data.LiveDataActivity
import com.hust_twj.zademo.jet_pack.live_data.UserViewModel
import com.hust_twj.zademo.jet_pack.view_model.ViewModelActivity
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

        tv_view_model.setOnClickListener {
            startActivity(Intent(this@JetPackActivity, ViewModelActivity::class.java))
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