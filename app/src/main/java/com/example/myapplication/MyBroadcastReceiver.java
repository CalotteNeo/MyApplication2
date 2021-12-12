package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 这个方法没有奏效，晚点再看看是咋回事。是不是静态注册没成功？——2021-12-12
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"MyBroadcastReceiver接收到了",Toast.LENGTH_LONG).show();
    }
}
