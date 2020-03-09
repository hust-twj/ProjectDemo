package com.hust_twj.zademo.moments_2_0.hot_topic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.moments_2_0.HotTopicAdapter;
import com.hust_twj.zademo.moments_2_0.HotTopicEntity;

import java.util.ArrayList;

/**
 *  热门话题列表页
 * Created by hust_twj on 2018/5/19.
 */

public class HotTopicActivity extends Activity{

    private RecyclerView mRvHotTopic;
    private HotTopicAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moments_hot_topic);

        findViews();

        initViewData();
    }

    private void findViews() {
        mRvHotTopic = findViewById(R.id.rv_hot_topic);
    }

    private void initViewData() {
        mRvHotTopic.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new HotTopicAdapter(this);
        mRvHotTopic.setAdapter(mAdapter);

        ArrayList<HotTopicEntity> mDataList = new ArrayList<>();
        HotTopicEntity entity1 = new HotTopicEntity();
        entity1.topicTitle = "#我的爱情故事";
        entity1.topicDesc = "100人参加";
        mDataList.add(entity1);

        HotTopicEntity entity2 = new HotTopicEntity();
        entity2.topicTitle = "#我的爱情故事2";
        entity2.topicDesc = "101人参加";
        mDataList.add(entity2);

        HotTopicEntity entity3 = new HotTopicEntity();
        entity3.topicTitle = "#我的爱情故事3";
        entity3.topicDesc = "104人参加";
        mDataList.add(entity3);

        mAdapter.setData(mDataList);

        mAdapter.setOnItemClickListener(new HotTopicAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(String topicTitle, int topicID) {
                Intent intent = new Intent();
                intent.putExtra("topic_title", topicTitle);
                intent.putExtra("topic_id", topicID);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
