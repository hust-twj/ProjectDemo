package com.hust_twj.zademo.third_part.rx_java;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava使用：
 */
public class RxJavaActivity extends AppCompatActivity {

    private Subscription mSubscription;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rx_java);

        //背压 取出缓存
        findViewById(R.id.tv_back_pressure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSubscription.request(2);
            }
        });

        /**
         * 方式1：完整式create创建Observable
         * 整体方法调用顺序：观察者.onSubscribe（）> 被观察者.subscribe（）> 观察者.onNext（）>观察者.onComplete()
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            // 1. 创建被观察者 & 生产事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            // 2. 通过通过订阅（subscribe）连接观察者和被观察者
            // 3. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("twj124", "开始采用subscribe连接");
            }

            @Override
            public void onNext(Integer integer) {
                LogUtils.d("twj124", "对Next事件" + integer + "作出响应");
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("twj124", "Error");
            }

            @Override
            public void onComplete() {
                LogUtils.d("twj124", "onComplete");
            }
        });


        /**
         * 方式2：just操作符快速创建Observable
         */
        // 1. 创建时传入 "haha1","haha2","haha3"
        // 在创建后就会发送这些对象，相当于执行了onNext("haha1")、onNext("haha2")、onNext("haha3")
        Observable.just("haha1", "haha2", "haha3")
                // 至此，一个Observable对象创建完毕，以下步骤仅为展示一个完整demo，可以忽略
                // 2. 通过通过订阅（subscribe）连接观察者和被观察者
                // 3. 创建观察者 & 定义响应事件的行为

                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.e("twj124", "onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.e("twj124", "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("twj124", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("twj124", "onComplete: ");
                    }
                });

        /**
         * 方式3：快速创建
         * 使用Consumer接口替代Observer接口，实现简便式的观察者模式
         * 只对被观察者发送的Next事件作出响应
         */

        Disposable disposable = Observable.just("Hello", "world")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        LogUtils.e("twj124", "accept: " + s);
                    }
                });

        /**
         * 方式4：采用Flowable背压
         * 异步订阅： 被观察者 & 观察者工作于不同的线程，默认缓存大小为128。先全部发送完毕，再接收、
         * 同步订阅：被观察者 & 观察者工作于同一线程，没有缓存。发送一个，接收一个。
         */
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                Log.e("Flowable", "可以接收的事件数：" + emitter.requested());
                Log.e("Flowable", "发送事件1");
                emitter.onNext(1);

                Log.e("Flowable", "发送事件2");
                emitter.onNext(2);

                Log.e("Flowable", "发送事件3");
                emitter.onNext(3);

                Log.e("Flowable", "发送事件4");
                emitter.onNext(4);

                Log.e("Flowable", "发送完成");
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.e("Flowable", "onSubscribe");
                        // 通过request方法控制流速
                        // 作用：决定观察者能够接收多少个事件
                        // 如设置了s.request(3)，这就说明观察者能够接收3个事件（多出的事件存放在缓存区）
                        // 官方默认推荐使用Long.MAX_VALUE，即s.request(Long.MAX_VALUE);
                        mSubscription = s;
                        //s.request(3);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("Flowable", "接收到了事件 " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("Flowable", "onError  " + t.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("Flowable", "onComplete");
                    }
                });
    }

}