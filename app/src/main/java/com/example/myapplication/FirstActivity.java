package com.example.myapplication;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TaskId是啥？通过activity.getTaskId()从我的正在运行的活动中获取应用ID
        Log.d("FirstActivity","Task-id是"+getTaskId());
        setContentView(R.layout.first_layout);
        Button button1 = (Button)findViewById(R.id.button1);
        // 用例：启动模式
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        // 用例1：显式/隐式Intent
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent("com.example.myapplication.ACTION_START");
////                Intent intent1 =  new Intent(FirstActivity.this,SecondActivity.class);
//                intent.addCategory("com.example.myapplication.MY_CATEGORY");
//                startActivity(intent);
//            }
//        });
//        // 用例2：其他隐式Intent
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);//调用系统浏览器
//                intent.setData(Uri.parse("tel:10086"));
//                startActivity(intent);
//            }
//        });
        // 用例3、传递数据给下一个活动
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String data = "Hello SecondActivity";
//                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
//                intent.putExtra("extra data",data);
//                startActivity(intent);
//            }
//        });
//        // 用例4：返回数据给上一个活动
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
//                startActivityForResult(intent,1);//请求码在onActivityResult中使用
//            }
//        });
//
//        // 用例1：活动的生命周期
//        Log.d("FirstActivity","onCreate");
//        Button start_normal_activity = findViewById(R.id.start_normal_activity);
//        Button start_dialog_activity = findViewById(R.id.start_dialog_activity);
//        start_normal_activity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(FirstActivity.this, NormalActivity.class);
//                startActivity(intent);
//            }
//        });
//        start_dialog_activity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(FirstActivity.this, DialogActivity.class);
//                startActivity(intent);
//            }
//        });
//        if (savedInstanceState != null){
//            String tempData = savedInstanceState.getString("data_key");
//            Log.d("FirstActivity",tempData);
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("FirstActivity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("FirstActivity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("FirstActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("FirstActivity","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("FirstActivity","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("FirstActivity","onRestart");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(FirstActivity.this,"你点击了添加",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this,"你点击了删除",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returenedData = data.getStringExtra("data return");
                    Log.d("FirstActivity", returenedData);
                }
                break;
            default:

        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "你刚刚输入的内容";
        outState.putString("data_key",tempData);
    }
}