package com.hust_twj.zademo.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.hust_twj.zademo.R
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

        //协程，子线程，模拟请求网络
        GlobalScope.launch (Dispatchers.IO){
            val content = fetchData()
            Log.e(TAG, "协程："+content + "  " + System.currentTimeMillis() + "  " + Thread.currentThread().name)

            //切换到主线程，更新UI
            withContext(Dispatchers.Main) {
                Log.e(TAG, "协程："+content + "  " + System.currentTimeMillis() + "  " + Thread.currentThread().name)

            }
        }
    }

    //suspend 方法能够使协程执行暂停，等执行完毕后在返回结果，同时不会阻塞线程。
    private suspend fun fetchData(): String {
        Log.e(TAG, "模拟子线程开始："+  System.currentTimeMillis() + "  " + Thread.currentThread().name)

        delay(2000)
        Log.e(TAG, "模拟子线程结束："+  System.currentTimeMillis() + "  " + Thread.currentThread().name)

        return "content"
    }

}