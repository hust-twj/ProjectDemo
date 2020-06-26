package com.hust_twj.zademo.third_part.rx_java;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.utils.LogUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava使用：
 */
public class RxJavaActivity extends AppCompatActivity implements View.OnClickListener {

    private Subscription mSubscription;

    @SuppressWarnings({"CheckResult"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rx_java);

        findViewById(R.id.tv_rx_java_create).setOnClickListener(this);

        //背压 取出缓存
        findViewById(R.id.tv_back_pressure).setOnClickListener(this);

        findViewById(R.id.tv_timer).setOnClickListener(this);
        findViewById(R.id.tv_interval).setOnClickListener(this);

        /**
         * 5、变换操作符：map()
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
            }
        })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "使用 Map变换操作符 将事件" + integer + "的参数从 整型" + integer + " 变换成 字符串类型" + integer;
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                LogUtils.e("map", s);
            }
        });


        /**
         * 6、Subscriber
         */
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };

        /**
         * 7、线程切换
         *
         * Thread run() 所在线程为 :Thread-32
         * Observer onSubscribe() 所在线程为 :Thread-32
         * Observable subscribe() 所在线程为 :RxCachedThreadScheduler-1
         * Observer onNext() 所在线程为 :main
         * Observer onNext() 所在线程为 :main
         * Observer onComplete() 所在线程为 :main
         */
        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.e("thread", "Thread run() 所在线程为 :" + Thread.currentThread().getName());

                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                        Log.e("thread", "Observable subscribe() 所在线程为 :" + Thread.currentThread().getName());
                        emitter.onNext("emitter 1");
                        emitter.onNext("emitter 2");
                        emitter.onComplete();
                    }
                })
                        .flatMap(new Function<String, ObservableSource<String>>() {
                            @Override
                            public ObservableSource<String> apply(String s) throws Exception {
                                final List<String> list = new ArrayList<>();
                                for (int i = 0; i < 3; i++) {
                                    list.add("我是事件 " + s + "拆分后的子事件" + i);
                                    // 通过flatMap中将被观察者生产的事件序列先进行拆分，再将每个事件转换为一个新的发送三个String事件
                                    // 最终合并，再发送给被观察者
                                }
                                return Observable.fromIterable(list);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.e("thread", "Observer onSubscribe() 所在线程为 :" + Thread.currentThread().getName());
                            }

                            @Override
                            public void onNext(String s) {
                                Log.e("thread", "Observer onNext() 所在线程为 :" + Thread.currentThread().getName() + "  " + s);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("thread", "Observer onError() 所在线程为 :" + Thread.currentThread().getName());
                            }

                            @Override
                            public void onComplete() {
                                Log.e("thread", "Observer onComplete() 所在线程为 :" + Thread.currentThread().getName());
                            }
                        });
            }
        }).start();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_rx_java_create:
                createClick();
                break;
            case R.id.tv_back_pressure:
                //背压 取出缓存
                mSubscription.request(2);
                break;
            case R.id.tv_timer:
                timerClick();
                break;

            case R.id.tv_interval:
                clickInterval(5);
                break;
            default:
                break;
        }
    }

    private void createClick() {
        /**
         * 方式1：完整式create创建Observable
         * 整体方法调用顺序：观察者.onSubscribe（）> 被观察者.subscribe（）> 观察者.onNext（）>观察者.onComplete()
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            // 1. 创建被观察者 & 生产事件
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d("create", "2  subscribe");
                Log.d("create", "3  发送事件1");
                emitter.onNext(1);
                Log.d("create", "发送事件2");
                emitter.onNext(2);
                Log.d("create", "发送事件3");
                emitter.onNext(3);
                Log.d("create", "发送事件 结束");
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            // 2. 通过通过订阅（subscribe）连接观察者和被观察者
            // 3. 创建观察者 & 定义响应事件的行为
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("create", "1  subscribe已经连接");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d("create", "对Next事件 " + integer + "作出响应");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("create", "Error");
            }

            @Override
            public void onComplete() {
                Log.d("create", "onComplete");
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
                        LogUtils.e("just", "onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtils.e("just", "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("just", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.e("just", "onComplete: ");
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
                        LogUtils.e("disposable", "accept: " + s);
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

    /**
     * 延迟操作
     */
    private void timerClick() {
        LogUtils.e("timer", "timer click" + "  " + System.currentTimeMillis());
        //timer延迟执行例子:如延迟5秒:
        Observable.timer(5, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                // LogUtils.e("timer", "timer onSubscribe" + "  " + System.currentTimeMillis());
            }

            @Override
            public void onNext(Long aLong) {
                LogUtils.e("timer", "timer onNext" + "  " + System.currentTimeMillis() + "  " + aLong);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("timer", "timer onError" + "  " + System.currentTimeMillis());
            }

            @Override
            public void onComplete() {
                //LogUtils.e("timer", "timer onComplete" + "  " + System.currentTimeMillis());
            }
        });
    }

    /**
     * 周期性地进行
     *
     * @param intervalSeconds 间隔周期 秒
     */
    private void clickInterval(int intervalSeconds) {
        CompositeDisposable disposable = new CompositeDisposable();
        //初始间隔0，每intervalSeconds触发一次
        Observable.interval(0, intervalSeconds, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onNext(Long aLong) {
                //总共执行6次
                if (aLong >= 5) {
                    disposable.dispose();
                }
                LogUtils.e("interval", "interval onNext" + "  " + System.currentTimeMillis() + "  " + aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
