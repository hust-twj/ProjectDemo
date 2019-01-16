package com.hust_twj.zademo.event_bus;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.event_bus.event.ChildEvent;
import com.hust_twj.zademo.event_bus.event.FatherEvent;
import com.hust_twj.zademo.event_bus.event.MyEvent;
import com.hust_twj.zademo.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class EventBusDemoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        setContentView(R.layout.activity_event_bus_demo);

        findViewById(R.id.tv_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //事件继承：post一个事件A时，若A是B的子类或者A实现了接口B，订阅B的订阅者也能接收到事件。
                EventBus.getDefault().post(new ChildEvent("haha")); //ChildEvent  FatherEvent 和 MyEvent 均可以收到

                // EventBus.getDefault().post(new FatherEvent("haha"));  -> FatherEvent 和 MyEvent可以收到
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventReceive(ChildEvent event){
        LogUtils.e("twj","this  ChildEvent receive: " +  event.msg);

    }

    @Subscribe
    public void onEventReceive(FatherEvent event){
        LogUtils.e("twj","this FatherEvent receive: " +  event.msg);

    }

    @Subscribe
    public void onEventReceive(MyEvent event){
        LogUtils.e("twj","this MyEvent receive: ");

    }

}
