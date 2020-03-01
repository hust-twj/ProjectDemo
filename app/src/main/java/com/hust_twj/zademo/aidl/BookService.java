package com.hust_twj.zademo.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.hust_twj.zademo.main.TagConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Description ：服务端：暴露方法给其他应用进行调用
 * Created by Wenjing.Tang on 2020/3/1.
 */
public class BookService extends Service {

    public static final String SERVICE_PACKAGE = "com.hust_twj.zademo.aidl.BookService";

    private List<Book> mBookList;

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        mBookList = new ArrayList<>();
        Book book1 = new Book(1, "活着");
        Book book2 = new Book(2, "霍乱时期的爱情");
        mBookList.add(book1);
        mBookList.add(book2);
    }

    /**
     * 根据 IBookManager.aidl 文件生成 IBookManager
     * mBinder 即为 Binder 的实例，客户端需要通过此与服务端进行交互
     */
    private IBookManager.Stub mBinder = new IBookManager.Stub() {

        @Override
        public void addBook(Book book) throws RemoteException {
            if (book == null) {
                Log.e(TagConstant.AIDL, "book null");
                return;
            }
            mBookList.add(book);
            Log.e(TagConstant.AIDL, String.format("添加了一本新书, 现在有%d本", mBookList.size()) +
                    "    currentThread = " + Thread.currentThread().getName());
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        /**
         * 在这里可以做权限认证，return false 意味着客户端的调用就会失败
         * 比如下面，只允许包名 SERVICE_PACKAGE常量 的客户端通过，其他apk将无法完成调用过程
         */
        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            String packageName = null;
            String[] packages = BookService.this.getPackageManager().
                    getPackagesForUid(getCallingUid());
            if (packages != null && packages.length > 0) {
                packageName = packages[0];
            }
            Log.e(TagConstant.AIDL, "onTransact: " + packageName);
//            if (!SERVICE_PACKAGE.equals(packageName)) {
//                return false;
//            }
            return super.onTransact(code, data, reply, flags);
        }
    };

    /**
     * 客户端调用 bindService()绑定时，
     * 客户端的 onServiceConnected() 回调会接收服务的 onBind() 方法所返回的 binder 实例。
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TagConstant.AIDL, "onBind  call");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        Log.e(TagConstant.AIDL, "onDestroy call");

        super.onDestroy();
    }
}
