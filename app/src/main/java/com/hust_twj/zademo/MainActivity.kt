package com.hust_twj.zademo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import com.hust_twj.zademo.jet_pack.JetPackActivity
import com.hust_twj.zademo.aidl.AidlActivity
import com.hust_twj.zademo.bitmap_opti.BitmapOptiActivity
import com.hust_twj.zademo.block_queue.BlockQueueActivity
import com.hust_twj.zademo.bottom_sheet.BottomSheetActivity
import com.hust_twj.zademo.download.DownloadActivity
import com.hust_twj.zademo.frame_layout.FrameActivity
import com.hust_twj.zademo.handler.MainHandlerActivity
import com.hust_twj.zademo.hook.HookActivity
import com.hust_twj.zademo.jvm.JVMActivity
import com.hust_twj.zademo.kotlin.KotlinActivity
import com.hust_twj.zademo.life_cycle.LifeCycleActivity
import com.hust_twj.zademo.line_space_extra.LineSpaceActivity
import com.hust_twj.zademo.list.ListActivity
import com.hust_twj.zademo.live_end.LiveEndActivity
import com.hust_twj.zademo.main.MainAdapter
import com.hust_twj.zademo.main.MainEntity
import com.hust_twj.zademo.main.TouchCallback
import com.hust_twj.zademo.moment_2_0_optima.SpannableActivity
import com.hust_twj.zademo.moments_2_0.hot_topic.MomentTopicDetailActivity
import com.hust_twj.zademo.moments_2_0.hot_topic.PublishActivity
import com.hust_twj.zademo.pay.PayActivity
import com.hust_twj.zademo.pic_text.PicTextActivity
import com.hust_twj.zademo.queen_heart.QueenHeartActivity
import com.hust_twj.zademo.remote_view.RemoteViewActivity
import com.hust_twj.zademo.round_img_view.RoundImgActivity
import com.hust_twj.zademo.share.ShareActivity
import com.hust_twj.zademo.span.SpanActivity
import com.hust_twj.zademo.third_part.ThirdPartActivity
import com.hust_twj.zademo.thread.ThreadActivity
import com.hust_twj.zademo.toast.ToastActivity
import com.hust_twj.zademo.ui_params.UIParamsActivity
import com.hust_twj.zademo.ui_widget.activity.UIWidgetActivity
import com.hust_twj.zademo.view.ViewActivity
import com.hust_twj.zademo.xfermode.XfermodeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    //val touchHelper? : ItemTouchHelper = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mAdapter = MainAdapter(this)
        rv_main.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        rv_main.adapter = mAdapter

        mAdapter.setOnlClickListener(object : MainAdapter.OnClickListener{
            override fun onClick(index: Int) {
                when(index){
                    //动态2.0
                    MainEntity.INDEX_PUBLISH -> startActivity(Intent(this@MainActivity, PublishActivity::class.java))

                    //动态2.0优化， Spannable测试
                    MainEntity.INDEX_SPANNABLE -> startActivity(Intent(this@MainActivity, SpannableActivity::class.java))

                    MainEntity.INDEX_MOMENT_DETAIL -> MomentTopicDetailActivity.start(this@MainActivity, 10)

                    MainEntity.INDEX_PIC_TEXT -> startActivity(Intent(this@MainActivity, PicTextActivity::class.java))

                    MainEntity.INDEX_LIST -> startActivity(Intent(this@MainActivity, ListActivity::class.java))

                    MainEntity.INDEX_QUEEN_HEART -> startActivity(Intent(this@MainActivity, QueenHeartActivity::class.java))

                    MainEntity.INDEX_LIVE_END -> startActivity(Intent(this@MainActivity, LiveEndActivity::class.java))

                    MainEntity.INDEX_SPAN -> startActivity(Intent(this@MainActivity, SpanActivity::class.java))

                    MainEntity.INDEX_TOAST -> startActivity(Intent(this@MainActivity, ToastActivity::class.java))

                    MainEntity.INDEX_FRAME -> startActivity(Intent(this@MainActivity, FrameActivity::class.java))

                    MainEntity.INDEX_LINE_SPACE -> startActivity(Intent(this@MainActivity, LineSpaceActivity::class.java))

                    MainEntity.INDEX_ROUND_IMAGE -> startActivity(Intent(this@MainActivity, RoundImgActivity::class.java))

                    MainEntity.INDEX_HANDLER -> startActivity(Intent(this@MainActivity, MainHandlerActivity::class.java))

                    MainEntity.INDEX_BOTTOM_SHEET -> startActivity(Intent(this@MainActivity, BottomSheetActivity::class.java))

                    MainEntity.INDEX_SHARE -> startActivity(Intent(this@MainActivity, ShareActivity::class.java))

                    MainEntity.INDEX_PAY -> startActivity(Intent(this@MainActivity, PayActivity::class.java))

                    MainEntity.INDEX_REMOTE_VIEW -> startActivity(Intent(this@MainActivity, RemoteViewActivity::class.java))

                    MainEntity.INDEX_XFERMODE -> startActivity(Intent(this@MainActivity, XfermodeActivity::class.java))

                    MainEntity.INDEX_BITMAP -> startActivity(Intent(this@MainActivity, BitmapOptiActivity::class.java))

                    MainEntity.INDEX_UI_WIDGET -> startActivity(Intent(this@MainActivity, UIWidgetActivity::class.java))

                    MainEntity.INDEX_AIDL -> startActivity(Intent(this@MainActivity, AidlActivity::class.java))

                    MainEntity.INDEX_VIEW -> startActivity(Intent(this@MainActivity, ViewActivity::class.java))

                    MainEntity.INDEX_UI_PARAMS -> startActivity(Intent(this@MainActivity, UIParamsActivity::class.java))

                    MainEntity.INDEX_DOWNLOAD -> startActivity(Intent(this@MainActivity, DownloadActivity::class.java))

                    MainEntity.INDEX_BLOCK_QUEUE -> startActivity(Intent(this@MainActivity, BlockQueueActivity::class.java))

                    MainEntity.INDEX_JVM -> startActivity(Intent(this@MainActivity, JVMActivity::class.java))

                    MainEntity.INDEX_THREAD -> startActivity(Intent(this@MainActivity, ThreadActivity::class.java))

                    MainEntity.INDEX_THIRD_PART -> startActivity(Intent(this@MainActivity, ThirdPartActivity::class.java))

                    MainEntity.INDEX_KOTLIN -> startActivity(Intent(this@MainActivity, KotlinActivity::class.java))

                    MainEntity.INDEX_ARCH -> startActivity(Intent(this@MainActivity, JetPackActivity::class.java))

                    MainEntity.INDEX_LIFE_CYCLE -> startActivity(Intent(this@MainActivity, LifeCycleActivity::class.java))

                    MainEntity.INDEX_HOOK -> startActivity(Intent(this@MainActivity, HookActivity::class.java))

                }

            }
        })

        //创建ItemTouchHelper
        val touchHelper = ItemTouchHelper(TouchCallback(mAdapter))
        touchHelper.attachToRecyclerView(rv_main)

    }
}
