package com.hust_twj.zademo.third_part.event_bus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.third_part.event_bus.event.ChildEvent;
import com.hust_twj.zademo.third_part.event_bus.event.FatherEvent;
import com.hust_twj.zademo.third_part.event_bus.event.MyEvent;
import com.hust_twj.zademo.third_part.event_bus.event.StickyEvent;
import com.hust_twj.zademo.utils.LogUtils;
import com.hust_twj.zademo.utils.ToastUtils;

import org.greenrobot.eventbus.Subscribe;

public class EventBusDemoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_bus_demo);

        findViewById(R.id.tv_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //事件继承：post一个事件A时，若A是B的子类或者A实现了接口B，订阅B的订阅者也能接收到事件。
                Event.post(new ChildEvent("haha")); //ChildEvent  FatherEvent 和 MyEvent 均可以收到

            }
        });

        //使用建造者模式，构建EventBus实例
        //eventBus = EventBus.builder().eventInheritance(true).build();
        Event.register(this);

    }

    public void sendSticky(View view) {
        Event.postSticky(new StickyEvent("粘性广播"));
        ToastUtils.toast(this, "已发送");
    }

    public void goTo(View view) {
        startActivity(new Intent(this, StickyActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Event.unregister(this);
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
        LogUtils.e("twj","this MyEvent receive: " + event.toString());

    }

    //允许一个类有多个参数相同的订阅方法。
    @Subscribe
    public void onEventReceive2(MyEvent event){
        LogUtils.e("twj","this MyEvent receive: " + event.toString());

    }

}
