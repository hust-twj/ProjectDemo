package com.hust_twj.zademo.third_part.okhttp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hust_twj.zademo.R
import com.hust_twj.zademo.utils.LogUtils
import kotlinx.android.synthetic.main.activity_okhttp.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * OkHttp使用：
 */
class OkHttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp)

        tv_sync.setOnClickListener {
            startActivity(Intent(this@OkHttpActivity, SyncRequestActivity::class.java))
        }

        tv_async.setOnClickListener {
            startActivity(Intent(this@OkHttpActivity, AsyncRequestActivity::class.java))

        }
    }
}