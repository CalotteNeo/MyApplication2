package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        TextView dialog_tv = (TextView) findViewById(R.id.dialog_tv);
        dialog_tv.setText("开启DialogActivity，点击BACK返回");
    }
}