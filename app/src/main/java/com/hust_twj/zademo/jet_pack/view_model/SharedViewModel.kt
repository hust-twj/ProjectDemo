package com.hust_twj.zademo.jet_pack.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay

/**
 * Description ：
 * Created by Wenjing.Tang on 2020-02-18.
 */
class SharedViewModel : ViewModel(), CoroutineScope by MainScope() {

    val liveData = MutableLiveData<GoodEntity>()

    fun setLiveData(entity: GoodEntity) {
        liveData.value = entity
    }

    fun getLiveData(): LiveData<GoodEntity> {
        return liveData
    }

    /**
     * 模拟网络请求
     */
    suspend fun requestData(): GoodEntity {
        delay(2000)
        return generateEntity()
    }

    private fun generateEntity(): GoodEntity {
        val goodEntity = GoodEntity()
        goodEntity.name = "十二生肖"
        goodEntity.resourceID = (Math.random() * 100).toInt()
        goodEntity.url = "https://www.baidu.com"
        return goodEntity
    }
}