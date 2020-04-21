package com.hust_twj.zademo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/4/20.
 */
class CoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        GlobalScope.launch {
//            requestForNetwork()
//            requestForDB()
//        }
    }

//    private suspend fun requestForNetwork() {
//        withContext(Dispatchers.IO) {
//            delay(1000)
//            LogUtils.e("twj125", "模拟网络请求")
//        }
//    }
//
//    private suspend fun requestForDB() {
//        withContext(Dispatchers.IO) {
//            delay(1000)
//            LogUtils.e("twj125", "模拟数据库操作")
//        }
//    }

}