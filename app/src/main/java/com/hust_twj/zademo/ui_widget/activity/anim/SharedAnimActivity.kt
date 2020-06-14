package com.hust_twj.zademo.ui_widget.activity.anim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hust_twj.zademo.R
import kotlinx.android.synthetic.main.activity_shared_anim.*

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/6/14.
 */
class SharedAnimActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_anim)

        rv.layoutManager = LinearLayoutManager(this)
        val adapter = SharedAnimAdapter(this)
        rv.adapter = adapter

        adapter.setOnlClickListener {

        }
    }
}