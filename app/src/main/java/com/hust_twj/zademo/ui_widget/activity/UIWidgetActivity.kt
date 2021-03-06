package com.hust_twj.zademo.ui_widget.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.hust_twj.zademo.R
import com.hust_twj.zademo.ui_widget.activity.anim.SharedAnimActivity
import com.hust_twj.zademo.ui_widget.activity.anim.SharedAnimDetailActivity
import com.hust_twj.zademo.ui_widget.activity.layout_manager.LayoutManagerActivity
import com.hust_twj.zademo.ui_widget.activity.motion_layout.MotionLayoutActivity
import com.hust_twj.zademo.ui_widget.activity.view_pager.ViewPager2Activity
import com.hust_twj.zademo.ui_widget.activity.view_pager.ViewPagerActivity
import com.hust_twj.zademo.ui_widget.adapter.UIAdapter
import com.hust_twj.zademo.ui_widget.bean.UIEntity
import com.hust_twj.zademo.ui_widget.widget.FuParamsAdjustPanel
import kotlinx.android.synthetic.main.activity_ui_widget.*

/**
 * Created by hust_twj
 * on 2019/2/26
 */
class UIWidgetActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_widget)

        val mAdapter = UIAdapter(this)
        rv_ui_widget.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        rv_ui_widget.adapter = mAdapter

        mAdapter.setOnlClickListener(object : UIAdapter.OnClickListener {
            override fun onClick(index: Int) {
                when (index) {
                    //动态2.0
                    UIEntity.INDEX_SEEK_BAR -> {
                        val paramsAdjustPanel = FuParamsAdjustPanel(this@UIWidgetActivity)
                        paramsAdjustPanel.popupFromBottom()
                    }

                    UIEntity.INDEX_PROGRESS_BAR -> startActivity(Intent(this@UIWidgetActivity, ProgressBarActivity::class.java))

                    UIEntity.INDEX_GRADIENT_RING -> startActivity(Intent(this@UIWidgetActivity, GradientRingActivity::class.java))

                    UIEntity.INDEX_FRAGMENT -> startActivity(Intent(this@UIWidgetActivity, FragmentActivity::class.java))

                    UIEntity.INDEX_COUNT_DOWN -> startActivity(Intent(this@UIWidgetActivity, ListCountDownActivity::class.java))

                    UIEntity.INDEX_ARC_RING -> startActivity(Intent(this@UIWidgetActivity, ArcRecycleViewActivity::class.java))

                    UIEntity.INDEX_ELLIPSE_RV -> startActivity(Intent(this@UIWidgetActivity, EllipseMenuActivity::class.java))

                    UIEntity.INDEX_CIRCLE_MENU -> startActivity(Intent(this@UIWidgetActivity, CircleMenuActivity::class.java))

                    UIEntity.INDEX_GRID_RV -> startActivity(Intent(this@UIWidgetActivity, GridRvActivity::class.java))

                    UIEntity.INDEX_CONSTRAINT_LAYOUT -> startActivity(Intent(this@UIWidgetActivity, ConstraintLayoutActivity::class.java))

                    UIEntity.INDEX_CONSTRAINT_GROUP -> startActivity(Intent(this@UIWidgetActivity, ConstraintGroupActivity::class.java))

                    UIEntity.INDEX_MOTION_LAYOUT -> startActivity(Intent(this@UIWidgetActivity, MotionLayoutActivity::class.java))

                    UIEntity.INDEX_TAB_LAYOUT_RV -> startActivity(Intent(this@UIWidgetActivity, TablayoutRvActivity::class.java))

                    UIEntity.INDEX_LIFE_CYCLE_WINDOW -> startActivity(Intent(this@UIWidgetActivity, LifecycleWindowActivity::class.java))

                    UIEntity.INDEX_GRADIENT_DRAWABLE -> startActivity(Intent(this@UIWidgetActivity, GradientDrawableActivity::class.java))

                    UIEntity.INDEX_VIEW_PAGER_2 -> startActivity(Intent(this@UIWidgetActivity, ViewPager2Activity::class.java))

                    UIEntity.INDEX_LAYOUT_MANAGER -> startActivity(Intent(this@UIWidgetActivity, LayoutManagerActivity::class.java))

                    UIEntity.INDEX_TOUCH_DELEGATE -> startActivity(Intent(this@UIWidgetActivity, TouchDelegateActivity::class.java))

                    UIEntity.INDEX_VIEW_PAGER -> startActivity(Intent(this@UIWidgetActivity, ViewPagerActivity::class.java))

                    UIEntity.INDEX_RECYCLE_VIEW_SCROLLER-> startActivity(Intent(this@UIWidgetActivity, RvScrollerActivity::class.java))

                    UIEntity.INDEX_SHARED_ANIM-> startActivity(Intent(this@UIWidgetActivity, SharedAnimActivity::class.java))

                    UIEntity.INDEX_SHARED_ANIM_DETAIL-> startActivity(Intent(this@UIWidgetActivity, SharedAnimDetailActivity::class.java))

                }

            }
        })

        //创建ItemTouchHelper
//        val touchHelper = ItemTouchHelper(TouchCallback(mAdapter))
//        touchHelper.attachToRecyclerView(rv_main)

    }

}
