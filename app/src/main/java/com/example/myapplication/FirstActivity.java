package com.example.myapplication;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TaskId是啥？通过activity.getTaskId()从我的正在运行的活动中获取应用ID
        Log.d("FirstActivity","Task-id是"+getTaskId());
        setContentView(R.layout.first_layout);
        Button button1 = (Button)findViewById(R.id.button1);
//        // 用例：启动模式
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SecondActivity.actionStart(FirstActivity.this,"data1","data2");
//            }
//        });
        // 用例1：显式/隐式Intent
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.myapplication.ACTION_START");
//                Intent intent1 =  new Intent(FirstActivity.this,SecondActivity.class);
                intent.addCategory("com.example.myapplication.MY_CATEGORY");
                startActivity(intent);
            }
        });
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
        // 用例1：活动的生命周期
        Log.d("FirstActivity","onCreate");
        Button start_normal_activity = findViewById(R.id.start_normal_activity);
        Button start_dialog_activity = findViewById(R.id.start_dialog_activity);
        start_normal_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, NormalActivity.class);
                startActivity(intent);
            }
        });
        start_dialog_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
//        if (savedInstanceState != null){
//            String tempData = savedInstanceState.getString("data_key");
//            Log.d("FirstActivity",tempData);
//        }
        Button control_progress = (Button) findViewById(R.id.control_progress);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        control_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3.2 用例：点击按钮修改progressbar的可见性
//                if (progressBar.getVisibility()==View.GONE){
//                    progressBar.setVisibility(View.VISIBLE);
//                }else {
//                    progressBar.setVisibility(View.GONE);
//                }
                int progress = progressBar.getProgress();
                progress = progress+10;
                progressBar.setProgress(progress);
                Toast toast = Toast.makeText(getApplicationContext(),"进度条+10了哦~",Toast.LENGTH_LONG);
//                toast.setGravity(Gravity.TOP,0,0);
                toast.show();

            }
        });
        Button popup_window = (Button)findViewById(R.id.popup_window);
        popup_window.setOnClickListener(this);
        Button start_layout_activity = (Button)findViewById(R.id.start_layout_activity);
        start_layout_activity.setOnClickListener(this);
        Button start_UICustomViews= (Button)findViewById(R.id.start_UICustomViews);
        start_UICustomViews.setOnClickListener(this);
        Button start_ListView= (Button)findViewById(R.id.start_ListView);
        start_ListView.setOnClickListener(this);
        Button start_BroadcastActivity= (Button)findViewById(R.id.start_BroadcastActivity);
        start_BroadcastActivity.setOnClickListener(this);
        Button send_broadcast = (Button) findViewById(R.id.send_broadcast);
        send_broadcast.setOnClickListener(this);
        edit = (EditText) findViewById(R.id.edit);
        String inputText = load();
        if (!TextUtils.isEmpty(inputText)) {
            edit.setText(inputText);
            // 将输入光标移动到文本的末尾位置
            edit.setSelection(inputText.length());
            Toast.makeText(this, "Restoring succeed", Toast.LENGTH_SHORT).show();
        }

        // sharedPreferences()
        Button saveData = (Button) findViewById(R.id.save_data);
        saveData.setOnClickListener(this);
        Button restore_data = (Button) findViewById(R.id.restore_data);
        restore_data.setOnClickListener(this);
        // 登录页
        Button start_login = (Button) findViewById(R.id.start_login);
        start_login.setOnClickListener(this);
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
        String inputText = edit.getText().toString();
        save(inputText);
    }

    private void save(String inputText) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            // 文件命名为data
            out = openFileOutput("data", Context.MODE_PRIVATE);
            // java流的方式来写
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer!=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public String load(){
        FileInputStream in = null;
        // 用BufferedReader来一行行地读取
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null){
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.popup_window:
                AlertDialog.Builder dialog = new AlertDialog.Builder(FirstActivity.this);
                dialog.setTitle("提示框");
                dialog.setMessage("重要的事情说三遍");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
            case R.id.start_layout_activity:
                Intent intent = new Intent(FirstActivity.this,LayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.start_UICustomViews:
                Intent intent2 = new Intent(FirstActivity.this,UICustomViewsActivity.class);
                startActivity(intent2);
                break;
            case R.id.start_ListView:
                Intent intent3 = new Intent(FirstActivity.this,ListViewActivity.class);
                startActivity(intent3);
                break;
            case R.id.start_BroadcastActivity:
                Intent intent4 = new Intent(FirstActivity.this,BroadcastActivity.class);
                startActivity(intent4);
                break;
            case R.id.send_broadcast:
                // Intent(自定义action)
                Intent intent5 = new Intent("com.example.myapplication.MY_BROADCAST");
                // 标准广播
                sendBroadcast(intent5);
                break;
            case R.id.save_data:
                // 通过getSharedPreferences()指定SharedPreferences的文件名为SharedData
                SharedPreferences.Editor editor = getSharedPreferences("SharedData",MODE_PRIVATE).edit();
                editor.putString("name","nizilin");
                editor.putInt("age", 26);
                editor.putBoolean("married",false);
                // apply()方法来提交
                editor.apply();
                break;
            case R.id.restore_data:
                SharedPreferences pref = getSharedPreferences("SharedData",MODE_PRIVATE);
                String name = pref.getString("name","");
                int age = pref.getInt("age",0);
                boolean married = pref.getBoolean("married",false);
                Toast.makeText(this,"姓名："+name+"-年龄："+age+"-婚姻状况："+married,Toast.LENGTH_SHORT).show();
                break;
            case R.id.start_login:
                Intent intent6 = new Intent(FirstActivity.this,LoginActivity.class);
                startActivity(intent6);
                break;
            default:
                break;
        }
    }
}