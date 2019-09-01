package com.hust_twj.zademo.jvm;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;


import com.hust_twj.zademo.R;
import com.hust_twj.zademo.jvm.utils.KeyedWeakReference;
import com.hust_twj.zademo.utils.LogUtils;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by Wenjing.Tang
 * on 2019/9/1
 */
public class ReferenceQueueActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reference_queue);

        // test2();
    }

    private void test2() {
        ReferenceQueue queue = new ReferenceQueue();
        // 创建弱引用，此时状态为Active，并且Reference.pending为空，当前Reference.queue = 上面创建的queue，并且next=null
        WeakReference reference = new WeakReference<Object>(new Object(), queue);
        LogUtils.e("twj124", reference);
        // 当GC执行后，由于是弱引用，所以回收该object对象，并且置于pending上，此时reference的状态为PENDING
        runGc();

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

    public void gcTrigger(View view) {
        referenceQueueTest();
    }

    @SuppressWarnings("unchecked")
    private void referenceQueueTest() {
        ReferenceQueue mReferenceQueue = new ReferenceQueue<>();

        Activity activity1 = new Activity();
        Activity activity2 = new Activity();

        // 定义一个弱引用对象引用, 并指定引用队列为 mReferenceQueue
        KeyedWeakReference weakReference1 = new KeyedWeakReference(activity1, "1", "activity1", mReferenceQueue);
        KeyedWeakReference weakReference2 = new KeyedWeakReference(activity2, "2", "activity2", mReferenceQueue);

        //去除强引用
        activity1 = null;
        activity2 = null;

        // 触发GC
        runGc();

        KeyedWeakReference reference;

        // 遍历 mReferenceQueue，取出所有弱引用
        while ((reference = (KeyedWeakReference) mReferenceQueue.poll()) != null) {
            Log.e("twj", "key: " + reference.key + " | name: " + reference.name);
        }
    }

    public void runGc() {
        // Code taken from AOSP FinalizationTest:
        // https://android.googlesource.com/platform/libcore/+/master/support/src/test/java/libcore/
        // java/lang/ref/FinalizationTester.java
        // System.gc() does not garbage collect every time. Runtime.gc() is
        // more likely to perform a gc.
        Runtime.getRuntime().gc();
        enqueueReferences();
        System.runFinalization();
    }

    private void enqueueReferences() {
        // Hack. We don't have a programmatic way to wait for the reference queue daemon to move
        // references to the appropriate queues.
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new AssertionError();
        }
    }


}
