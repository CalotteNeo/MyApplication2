package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends BasicActivity {
    // 2.6 启动活动的最佳写法:
    // 新增一个actionStart()方法，将启动SecondActivity所需要的参数，比如data1和data2暴露给调用方，
    // 这样调用方就知道启动SecondActivity的时候需要传什么参数了。
    public static void actionStart(Context context,String data1,String data2){
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("param1", data1);
        intent.putExtra("param2", data2);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SecondActivity","Task-id是"+getTaskId());
        setContentView(R.layout.second_layout);
        Button button2 = (Button) findViewById(R.id.button2);
//        Intent intent = getIntent();
//        String data = intent.getStringExtra("extra data");
//        Log.d("SecondActivity",data);
//        // 用例4：通过点击按钮返回数据给上一个活动
//
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.putExtra("data return","hello FirstActivity");
//                setResult(RESULT_OK,intent);
//                finish();
//            }
//        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
    // 用例5：通过点击返回键（BACK）返回数据给上一个活动
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("data return","你好，firstActivity，我从Back键返回");
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondActivity","onDestroy");
    }
}