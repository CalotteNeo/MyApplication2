package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

/*
5.2 例子：先通过动态注册的方式编写一个能够监听网络变化的程序。
 */
public class BroadcastActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_layout);
        intentFilter = new IntentFilter();
        // 疑问：intentFilter的Action（安卓底层提供的这些）在哪可以查到？
        // 网络状态变化时，系统发出的正是一条值为"android.net.conn.CONNECTIVITY_CHANGE"的广播，
        // 我们想要监听这个广播，所以就在这里添加响应的action
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        // 动态注册广播接收器
        registerReceiver(networkChangeReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁活动的时候把广播接收器注销
        unregisterReceiver(networkChangeReceiver);
    }

    /*
        定义一个内部类：NetworkChangeReceiver，用来监听网络状态变化
         */
    class NetworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectionManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context, "现在有网络",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "现在网络不可用", Toast.LENGTH_SHORT).show();
            }
        }
    }
}