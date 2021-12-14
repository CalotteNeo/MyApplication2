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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity{

    private EditText edit;
    private List<Button> ButtonList = new ArrayList<>();
    private  Context mcontext = FirstActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TaskId是啥？通过activity.getTaskId()从我的正在运行的活动中获取应用ID
        Log.d("FirstActivity","Task-id是"+getTaskId());
        // 将layout改称ListView，新增的按钮都在FirstItemAdapter中处理逻辑
        setContentView(R.layout.first_layout);
        initButtons();//初始化子项目数据
        FirstItemAdapter adapter = new FirstItemAdapter(FirstActivity.this, R.layout.first_item, ButtonList);
        ListView listView = (ListView) findViewById(R.id.first_list_view);
        listView.setAdapter(adapter);
/////////////////////////////////////////////////////////////////////////////////////////////////////////
//        Button button1 = (Button)findViewById(R.id.button1);
//        // 用例：启动模式
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SecondActivity.actionStart(FirstActivity.this,"data1","data2");
//            }
//        });
//        // 用例1：显式/隐式Intent
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
        // 用例1：活动的生命周期
        Log.d("FirstActivity","onCreate");
//        if (savedInstanceState != null){
//            String tempData = savedInstanceState.getString("data_key");
//            Log.d("FirstActivity",tempData);
//        }
        Button control_progress = (Button) findViewById(R.id.control_progress);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
//        control_progress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 3.2 用例：点击按钮修改progressbar的可见性
////                if (progressBar.getVisibility()==View.GONE){
////                    progressBar.setVisibility(View.VISIBLE);
////                }else {
////                    progressBar.setVisibility(View.GONE);
////                }
//                int progress = progressBar.getProgress();
//                progress = progress+10;
//                progressBar.setProgress(progress);
//                Toast toast = Toast.makeText(getApplicationContext(),"进度条+10了哦~",Toast.LENGTH_LONG);
////                toast.setGravity(Gravity.TOP,0,0);
//                toast.show();
//
//            }
//        });

        edit = (EditText) findViewById(R.id.edit);
        String inputText = load();
        if (!TextUtils.isEmpty(inputText)) {
            edit.setText(inputText);
            // 将输入光标移动到文本的末尾位置
            edit.setSelection(inputText.length());
            Toast.makeText(this, "Restoring succeed", Toast.LENGTH_SHORT).show();
        }
    }

    private void initButtons() {
        Button button1 = new Button(mcontext);
        button1.setText("Button1");
        button1.setId(R.id.button1);
        ButtonList.add(button1);
        Button start_normal_activity = new Button(mcontext);
        start_normal_activity.setText("开启normal活动");
        start_normal_activity.setId(R.id.start_normal_activity);
        ButtonList.add(start_normal_activity);
        Button start_dialog_activity = new Button(mcontext);
        start_dialog_activity.setText("开启dialog活动");
        start_dialog_activity.setId(R.id.start_dialog_activity);
        ButtonList.add(start_dialog_activity);
        Button control_progress = new Button(mcontext);
        control_progress.setText("控制进度条");
        control_progress.setId(R.id.control_progress);
        ButtonList.add(control_progress);
        Button popup_window = new Button(mcontext);
        popup_window.setText("点击弹出弹框");
        popup_window.setId(R.id.popup_window);
        ButtonList.add(popup_window);
        Button start_layout_activity = new Button(mcontext);
        start_layout_activity.setText("点击跳转布局页面");
        start_layout_activity.setId(R.id.start_layout_activity);
        ButtonList.add(start_layout_activity);
        Button start_UICustomViews = new Button(mcontext);
        start_UICustomViews.setText("点击跳转自定义控件页面");
        start_UICustomViews.setId(R.id.start_UICustomViews);
        ButtonList.add(start_UICustomViews);
        Button start_ListView = new Button(mcontext);
        start_ListView.setText("点击跳转ListView界面");
        start_ListView.setId(R.id.start_ListView);
        ButtonList.add(start_ListView);
        Button start_UIBestPractice = new Button(mcontext);
        start_UIBestPractice.setText("点击打开编写界面的最佳实践——聊天界面（未完成）");
        start_UIBestPractice.setId(R.id.start_UIBestPractice);
        ButtonList.add(start_UIBestPractice);
        Button start_BroadcastActivity = new Button(mcontext);
        start_BroadcastActivity.setText("点击打开广播练习页面");
        start_BroadcastActivity.setId(R.id.start_BroadcastActivity);
        ButtonList.add(start_BroadcastActivity);
        Button send_broadcast = new Button(mcontext);
        send_broadcast.setText("发送自定义广播");
        send_broadcast.setId(R.id.send_broadcast);
        ButtonList.add(send_broadcast);
        Button save_data = new Button(mcontext);
        save_data.setText("用SharedPreferences保存data");
        save_data.setId(R.id.save_data);
        ButtonList.add(save_data);
        Button restore_data = new Button(mcontext);
        restore_data.setText("恢复保存的SharedPreferences数据");
        restore_data.setId(R.id.restore_data);
        ButtonList.add(restore_data);
        Button start_login = new Button(mcontext);
        start_login.setText("跳转到登录页");
        start_login.setId(R.id.start_login);
        ButtonList.add(start_login);



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
}