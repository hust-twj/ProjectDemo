package com.hust_twj.zademo.moments_2_0.hot_topic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.hust_twj.zademo.R;

/**
 *
 * Created by hust_twj on 2018/5/20.
 */

public class PublishActivity extends Activity implements View.OnClickListener{

    private TextView mTvSelectTopic;
    private TextView mTvTopicSelected;
    private int topicID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moments_publish);

        mTvSelectTopic = findViewById(R.id.tv_select_topic);
        mTvTopicSelected = findViewById(R.id.tv_topic_selected);

        mTvSelectTopic.setOnClickListener(this);
        mTvTopicSelected.setOnClickListener(this);

        ImmersionBar.with(this).init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_select_topic:
                Intent intent = new Intent(PublishActivity.this, HotTopicActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_topic_selected:
                mTvTopicSelected.setVisibility(View.GONE);
                mTvSelectTopic.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK){
            String title = data.getStringExtra("topic_title");
            topicID = data.getIntExtra("topic_id", 0) ;

            mTvSelectTopic.setVisibility(View.GONE);
            mTvTopicSelected.setVisibility(View.VISIBLE);
            mTvTopicSelected.setText(title);
        }
    }
}
