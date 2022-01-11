package com.hust_twj.zademo.third_part.lancet;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hust_twj.zademo.utils.ToastUtils;

import me.ele.lancet.base.Origin;
import me.ele.lancet.base.Scope;
import me.ele.lancet.base.annotations.Insert;
import me.ele.lancet.base.annotations.Proxy;
import me.ele.lancet.base.annotations.TargetClass;

/**
 * Description ：
 * Created by Wenjing.Tang on 2022/1/10.
 */
public class HookUtil {

    @Proxy("i")
    @TargetClass("android.util.Log")
    public static int anyName(String tag, String msg){
        msg = msg + "  lancet hook";
        return (int) Origin.call();
    }

    @TargetClass(value = "androidx.appcompat.app.AppCompatActivity", scope = Scope.LEAF)
    @Insert(value = "onCreate", mayCreateSuper = true)
    public void _onCreate(Bundle savedInstanceState) {
        Log.i("twj124", "lancet hook onCreate");
        Origin.callVoid();
    }

    @TargetClass(value = "androidx.appcompat.app.AppCompatActivity", scope = Scope.LEAF)
    @Insert(value = "onStop", mayCreateSuper = true)
    public void onStop() {
        Log.i("twj124", "lancet hook onStop");
        Origin.callVoid();
    }

    @TargetClass(value = "androidx.appcompat.app.AppCompatActivity", scope = Scope.LEAF)
    @Insert(value = "onDestroy", mayCreateSuper = true)
    public void onDestroy() {
        Log.i("twj124", "lancet hook onDestroy");
        Origin.callVoid();
    }
}
