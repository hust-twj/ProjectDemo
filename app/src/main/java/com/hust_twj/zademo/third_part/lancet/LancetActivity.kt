package com.hust_twj.zademo.third_part.lancet

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hust_twj.zademo.R
import kotlinx.android.synthetic.main.activity_lancet.*

/**
 * Description ：
 * Created by hust_twj on 2022/1/10.
 */
class LancetActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lancet)

        Log.e("twj124", "LancetActivity onCreate called")

        tv_lancet.setOnClickListener {
            Log.i("twj124", "textview click")
        }
    }

//    override fun onStop() {
//        super.onStop()
//        Log.e("twj124", "LancetActivity onStop called")
//    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("twj124", "LancetActivity onDestroy called")
    }

}