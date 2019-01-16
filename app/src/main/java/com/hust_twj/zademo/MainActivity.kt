package com.hust_twj.zademo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hust_twj.zademo.event_bus.EventBusDemoActivity
import com.hust_twj.zademo.frame_layout.FrameActivity
import com.hust_twj.zademo.line_space_extra.LineSpaceActivity
import com.hust_twj.zademo.list.ListActivity
import com.hust_twj.zademo.live_end.LiveEndActivity
import com.hust_twj.zademo.moment_2_0_optima.SpannableActivity
import com.hust_twj.zademo.moments_2_0.hot_topic.MomentTopicDetailActivity
import com.hust_twj.zademo.moments_2_0.hot_topic.PublishActivity
import com.hust_twj.zademo.pic_text.PicTextActivity
import com.hust_twj.zademo.queen_heart.QueenHeartActivity
import com.hust_twj.zademo.round_img_view.RoundImgActivity
import com.hust_twj.zademo.span.SpanActivity
import com.hust_twj.zademo.toast.ToastActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //动态2.0
        moment_2_0.setOnClickListener {
            startActivity(Intent(this, PublishActivity::class.java))
        }

        //动态2.0优化， Spannable测试
        moment_2_0_optima.setOnClickListener {
            startActivity(Intent(this, SpannableActivity::class.java))
        }

        moment_topic_detail.setOnClickListener {
            MomentTopicDetailActivity.start(this, 10)
        }

        pic_text.setOnClickListener {
            startActivity(Intent(this, PicTextActivity::class.java))
        }
        list.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }

        queen_heart.setOnClickListener{
            startActivity(Intent(this, QueenHeartActivity::class.java))
        }

        live_end.setOnClickListener{
            startActivity(Intent(this, LiveEndActivity::class.java))
        }
        span.setOnClickListener{
            startActivity(Intent(this, SpanActivity::class.java))
        }

        toast.setOnClickListener{
            startActivity(Intent(this, ToastActivity::class.java))
        }

        frame_layout.setOnClickListener{
            startActivity(Intent(this, FrameActivity::class.java))
        }

        line_space.setOnClickListener{
            startActivity(Intent(this, LineSpaceActivity::class.java))
        }

        round_img_view.setOnClickListener{
            startActivity(Intent(this, RoundImgActivity::class.java))
        }

        event_bus.setOnClickListener{
            startActivity(Intent(this, EventBusDemoActivity::class.java))
        }

    }
}
