package com.hust_twj.zademo.jet_pack.view_model.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hust_twj.zademo.R
import com.hust_twj.zademo.jet_pack.view_model.SharedViewModel
import com.hust_twj.zademo.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Description ：
 * Created by Wenjing.Tang on 2020/4/29.
 */
class Fragment_2 : Fragment(), CoroutineScope by MainScope() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity is FragmentActivity) {
            sharedViewModel = ViewModelProviders.of(activity as FragmentActivity).get(SharedViewModel::class.java)

            //  sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)
        }
        btn_change.setOnClickListener {
            launch {
                val entity = async { sharedViewModel.requestData() }
                sharedViewModel.setLiveData(entity.await())
            }

        }
        sharedViewModel.liveData.observe(this,
                Observer {
                    LogUtils.e("twj124", "观察到了数据改变 ${it.toString()}")
                    tv_content.text = it.toString()
                })

    }

}