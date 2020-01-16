package com.hust_twj.zademo.moments_2_0.hot_topic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.moments_2_0.HotTopicAdapter;
import com.hust_twj.zademo.moments_2_0.HotTopicEntity;
import com.hust_twj.zademo.utils.IntentConstants;

import java.util.ArrayList;

/**
 * 话题详情页
 * Created by wenjing.tang on 2018/6/22.
 */

public class MomentTopicDetailActivity extends AppCompatActivity implements View.OnClickListener,AppBarLayout.OnOffsetChangedListener {

    private static final int SOURCE_FROM_HOT_LIST = 1; //热门列表
    private static final int SOURCE_FROM_PUBLIST = 2; //发布页

    private int hotTopicID;

    private RecyclerView mRvHotTopic;
    private HotTopicAdapter mAdapter;

    private TextView mTvPublish;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private int mLastVerticalOffset;

    private TextView mTvTopicTitle;
    private TextView mTvTopicPeople;
    private TextView mTvTopicDesc;

    private ImageView mIvToolbarLeft;
    private ImageView mIvToolbarRight;

    // TODO: 2018/6/21  topicID int long
    public static void start(Context context, int topicID){
        Intent intent = new Intent(context, MomentTopicDetailActivity.class);
        intent.putExtra(IntentConstants.HOT_TOPIC_ID, topicID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment_topic_detail_3);
        //mTvPublish = findViewById(R.id.tv_publish_moment);

        //mTvPublish.setOnClickListener(this);

        findViews();

        initViewData();

        if (getIntent() != null){
            hotTopicID = getIntent().getIntExtra(IntentConstants.HOT_TOPIC_ID, 0);
        }

        bindListeners();

    }

    private void findViews() {
        mRvHotTopic = findViewById(R.id.rv_list);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        appBarLayout = findViewById(R.id.app_bar_layout);
        toolbar = findViewById(R.id.tool_bar);
        collapsingToolbarLayout.setTitle("#爱情宣言#");
        toolbar.setTitle("");

        mIvToolbarLeft = findViewById(R.id.iv_tool_bar_left);
        mIvToolbarRight = findViewById(R.id.iv_tool_bar_right);

        mTvTopicTitle = findViewById(R.id.tv_hot_topic_title);
        mTvTopicPeople = findViewById(R.id.tv_hot_topic_people);
        mTvTopicDesc = findViewById(R.id.tv_hot_topic_desc);
    }

    private void initViewData() {
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.color_4d4d4d));

        mRvHotTopic.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new HotTopicAdapter(this);
        mRvHotTopic.setAdapter(mAdapter);

        ArrayList<HotTopicEntity> mDataList = new ArrayList<>();
        HotTopicEntity entity1 = new HotTopicEntity();
        entity1.topicTitle = "#我的爱情故事";
        entity1.topicDesc = "100人参加";

        for (int i = 0; i < 20; i++){
            mDataList.add(entity1);
        }

        mAdapter.setData(mDataList);

        mTvTopicTitle.setText("#爱情宣言#");
        mTvTopicTitle.getPaint().setFakeBoldText(true);
        mTvTopicPeople.setText("50人参与");
        mTvTopicPeople.getPaint().setFakeBoldText(true);
        mTvTopicDesc.setText("你好啊");
    }

    private void bindListeners() {
        appBarLayout.addOnOffsetChangedListener(this);
        mIvToolbarLeft.setOnClickListener(this);
        mIvToolbarRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           /* case R.id.tv_publish_moment:
                HotTopicEntity entity = new HotTopicEntity();
                entity.topicTitle = "#我的爱情宣言";
                entity.topicID = 100;
               // PublishActivity.start(this, true, PublishActivity.FROM_HOT_TOPIC_DETAIL, entity);
                break;*/
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (toolbar != null) {
            if (verticalOffset == 0) {
               // mSwipeXRecyclerView.setRefreshEnable(true);
                mIvToolbarLeft.setImageResource(R.drawable.back_icon);
                mIvToolbarRight.setImageResource(R.drawable.icon_camera_release_moments);
               /* if (mImmersionBar != null && mLastVerticalOffset != verticalOffset) {
                    setImmersionBarDark(false);
                }*/
            } else {
               // mSwipeXRecyclerView.setRefreshEnable(false);
                if (verticalOffset <= appBarLayout.getTotalScrollRange()) {
                    mIvToolbarLeft.setImageResource(R.drawable.icon_purple_back);
                    mIvToolbarRight.setImageResource(R.drawable.icon_camera_release_moments_purple);
                }
               /* if (mImmersionBar != null && mLastVerticalOffset != verticalOffset) {
                    setImmersionBarDark(true);
                }*/
            }
        }
        mLastVerticalOffset = verticalOffset;
    }
}
