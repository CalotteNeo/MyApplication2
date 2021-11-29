package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ThirdActivity","Task-id是"+getTaskId());
        setContentView(R.layout.third_layout);
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2.6 不管你想在什么地方退出程序，只需要调用ActivityCollector.finishAll()就可以了。
                ActivityCollector.finishAll();
            }
        });
    }
}