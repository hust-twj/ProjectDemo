package com.hust_twj.zademo.ui_widget.activity.anim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hust_twj.zademo.R
import kotlinx.android.synthetic.main.activity_shared_anim_detail.*

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/6/14.
 */
class SharedAnimDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_anim_detail)

        val bundle = intent.extras
        val entity: SharedAnimEntity? = bundle?.getSerializable("shared_entity") as SharedAnimEntity
        if (entity != null) {
            iv_icon.setBackgroundResource(entity.bg)
            tv_title.text = entity.title
        }
    }
}