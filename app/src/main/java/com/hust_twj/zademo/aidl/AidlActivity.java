package com.hust_twj.zademo.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hust_twj.zademo.R;
import com.hust_twj.zademo.main.TagConstant;

/**
 * 客户端：绑定服务端的Service来进行交互
 * Created by hust_twj
 * on 2019/3/15
 */
public class AidlActivity extends Activity implements View.OnClickListener {

    private IBookManager mIBookManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        TextView mTvBind = findViewById(R.id.tv_bind_service);
        TextView mTvUnbind = findViewById(R.id.tv_unbind_service);
        TextView mTvAddBook = findViewById(R.id.tv_add_book);

        mTvBind.setOnClickListener(this);
        mTvUnbind.setOnClickListener(this);
        mTvAddBook.setOnClickListener(this);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TagConstant.AIDL, "onServiceConnected" +
                    "     线程：" + Thread.currentThread().getName());
            //asInterface：将返回的参数转换成 YourServiceInterface 类型
            mIBookManager = IBookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TagConstant.AIDL, "onServiceDisconnected");
            mIBookManager = null;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bind_service:
                bindService();
                break;
            case R.id.tv_unbind_service:
                if (mConnection != null) {
                    unbindService(mConnection);
                }
                break;
            case R.id.tv_add_book:
                addBook();
                break;
            default:
                break;
        }

    }

    /**
     * 绑定服务
     * 调用 bindService()绑定时，客户端的 onServiceConnected() 回调会接收服务的 onBind() 方法所返回的 binder 实例。
     */
    public void bindService() {
        Intent intent = new Intent();
        intent.setAction(BookService.SERVICE_PACKAGE);
        intent.setPackage(getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    public void addBook() {
        Log.e(TagConstant.AIDL, "点击 添加 按钮");
        if (mIBookManager == null) {
            return;
        }
        try {
            //客户端调用服务端的方法
            mIBookManager.addBook(new Book(18, "漫画书"));
            Log.e(TagConstant.AIDL, "添加成功");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

