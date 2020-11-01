package com.hust_twj.zademo.third_part.dagger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hust_twj.zademo.R
import javax.inject.Inject

/**
 * Dagger使用：
 * Created by hust_twj on 2020-09-09.
 */
class DaggerActivity : AppCompatActivity() {

//    private var userManager : UserManager? = null

    // TODO: 2020/11/1 kt编译不通过
    //使用户依赖
//    @Inject
//    var apiService : ApiService? = null

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)

//        userManager =  UserManager()
         //注入、建立连接
//         DaggerUserComponent.create().register(this)
//
//         apiService?.register()
    }
}