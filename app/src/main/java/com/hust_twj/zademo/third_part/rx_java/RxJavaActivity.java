package com.hust_twj.zademo.third_part.rx_java;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hust_twj.zademo.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * RxJava使用：
 *
 */
public class RxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rx_java);

        Observable.just("Hello", "world")
                .subscribe(new Consumer<String>() {
                    @Override public void accept(String s) {
                        Log.e("twj124", "accept: " +s);
                    }
                });

        Observable.just("haha1","haha2","haha3")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("twj124", "onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("twj124", "onNext: " +s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("twj124", "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("twj124", "onComplete: ");
                    }
                })
                ;

    }

}
