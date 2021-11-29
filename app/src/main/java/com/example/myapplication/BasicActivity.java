package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BasicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("BasicActivity", getClass().getSimpleName());
        //2.6 将当前正在创建的活动添加到活动管理器里
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //2.6 将一个马上要销毁的活动从活动管理器里移除
        // 父类BasicActivity的onDestroy先执行，再执行子类的onDestroy
        Log.d("BasicActivity","onDestroy");
        ActivityCollector.removeActivity(this);
    }
}
