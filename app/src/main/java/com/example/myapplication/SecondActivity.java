package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
//        Intent intent = getIntent();
//        String data = intent.getStringExtra("extra data");
//        Log.d("SecondActivity",data);
        // 用例4：通过点击按钮返回数据给上一个活动
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data return","hello FirstActivity");
                setResult(RESULT_OK,intent);
                finish();
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
}