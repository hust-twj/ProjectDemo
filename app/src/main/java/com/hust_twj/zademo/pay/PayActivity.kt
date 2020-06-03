package com.hust_twj.zademo.pay

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.hust_twj.zademo.R
import com.hust_twj.zademo.share.LiveShareDialog
import com.hust_twj.zademo.utils.LogUtils
import kotlinx.android.synthetic.main.activity_pay.*
import java.math.BigDecimal

/**
 * Created by Wenjing.Tang
 * on 2019/1/31
 */
class PayActivity : Activity() {

    var coinCount: Float = 8.9f

    var coinDouble : Double = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_share)

        tv_share.setOnClickListener {
            val shareDialog = LiveShareDialog(this@PayActivity)
            shareDialog.popup()
        }

//        val currentCoin = 8.9f
//        var coin1 = 1.213f
//        var coin2 = 1.45f
//        LogUtils.e("twj124", coin1)
//        val sum = currentCoin - coinCount + coin1 + coin2
//        LogUtils.e("twj124", sum.toString() + "  " + (sum > 0))
//
//        var coin3 : Double  = 1.3
//        var coin4 : Double = 1.4
//        LogUtils.e("twj124", coinDouble + coin3 + coin4)

        var bigDecimal = BigDecimal("1")
        LogUtils.e("twj124", bigDecimal)
    }

}