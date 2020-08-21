package com.hust_twj.zademo.kotlin.collection

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hust_twj.zademo.R

/**
 *
 * @author hust_twj
 * @date 2020/8/21
 */
class CollectionktActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_coroutine)

        Log.e("twj124", findPerson(1, Person.generatePersonList(20))?.name)

        filter(Person.generatePersonList(20))

    }

    //1. 查找
    private fun findPerson(personID: Int, personList: ArrayList<Person>): Person? {
        if (personList == null || personList.size == 0) {
            return null
        }
        return personList.find { it.id == personID }
    }

    //2. 遍历
    private fun forEach(personList: ArrayList<Person>) {
        personList.forEach {
            Log.e("twj124", "${it.id}  " + it.name)
        }
    }

    //3. 过滤操作符
    private fun filter(personList: ArrayList<Person>) {
        //3.1 filter
        personList.filter {
            it.id % 2 == 0
        }.forEach {
            Log.e("twj124", "  ${it.id}  " + it.name)
        }

        //3.2 filterIndexed：根据Index过滤
        personList.filterIndexed { index, person ->
            index % 5 == 0
        }.forEach {
            Log.e("twj124", "  ${it.id}  " + it.name)
        }
    }

}