package com.hust_twj.zademo.jet_pack.view_model

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.hust_twj.zademo.R

/**
 * Description ：
 * Created by Wenjing.Tang on 2020-02-18.
 */


class ViewModelActivity : FragmentActivity() {

    private lateinit var mViewPager : ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        mViewPager = findViewById(R.id.view_pager2)

        val pagerAdapter = ModelFragmentAdapter(this)

        mViewPager.adapter = pagerAdapter
    }
}