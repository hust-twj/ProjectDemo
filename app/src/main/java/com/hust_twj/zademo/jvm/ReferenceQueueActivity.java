package com.hust_twj.zademo.jvm;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by Wenjing.Tang
 * on 2019/3/30
 */
public class ReferenceQueueActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reference_queue);

        test();

        // test2();
    }

    private void test2() {
        ReferenceQueue queue = new ReferenceQueue();
        // 创建弱引用，此时状态为Active，并且Reference.pending为空，当前Reference.queue = 上面创建的queue，并且next=null
        WeakReference reference = new WeakReference<Object>(new Object(), queue);
        LogUtils.e("twj124", reference);
        // 当GC执行后，由于是弱引用，所以回收该object对象，并且置于pending上，此时reference的状态为PENDING
        System.gc();

        /* ReferenceHandler从pending中取下该元素，并且将该元素放入到queue中，此时Reference状态为ENQUEUED，Reference.queue = ReferenceENQUEUED */

        /* 当从queue里面取出该元素，则变为INACTIVE，Reference.queue = Reference.NULL */
        Reference reference1 = null;
        try {
            reference1 = queue.remove();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.e("twj124", reference1);

    }

    private void test() {
        ReferenceQueue mReferenceQueue = new ReferenceQueue<>();
        // 定义一个弱引用对象引用, 并指定引用队列为 mReferenceQueue
        WeakReference<Object> weakReference = new WeakReference<Object>(new Object(), mReferenceQueue);
        // 触发应用进行垃圾回收
        Runtime.getRuntime().gc();

        // 延时1000ms,等待gc完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LogUtils.e("twj124", mReferenceQueue.toString());

        // 遍历 mReferenceQueue，取出所有弱引用
        while (mReferenceQueue.poll() != null) {
            LogUtils.e("twj124", "============ ref in queue");
        }
    }

}
