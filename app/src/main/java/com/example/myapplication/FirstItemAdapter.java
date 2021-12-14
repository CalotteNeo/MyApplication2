package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

/**
 * 自定义的适配器，这个适配器继承自ArrayAdapter，并将泛型指定为Button类
 */
public class FirstItemAdapter extends ArrayAdapter<Button> {
    private int resourceId;
    private Context mcontext;
    public FirstItemAdapter(Context context, int textViewResourceId, List<Button> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    /**
     * 该方法在每个子项被滚动到屏幕内的时候会被调用
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        mcontext = getContext();
        Button cur_button = getItem(position);//获取当前的musician实例
        // LayoutInflater这个方法，不是很懂
        // 不是很懂↓这个写法，但这是ListView中的标准写法，先这样吧。
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        Button first_button = (Button) view.findViewById(R.id.first_button);
        first_button.setText(cur_button.getText());
        first_button.setId(cur_button.getId());
        first_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button1:
                        Intent intent = new Intent(mcontext,SecondActivity.class);
                        mcontext.startActivity(intent);
                        break;
                    case R.id.start_normal_activity:
                        intent = new Intent(mcontext, NormalActivity.class);
                        mcontext.startActivity(intent);
                    case R.id.start_dialog_activity:
                        intent = new Intent(mcontext, DialogActivity.class);
                        mcontext.startActivity(intent);
                    case R.id.popup_window:
                        AlertDialog.Builder dialog = new AlertDialog.Builder(mcontext);
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
                        intent = new Intent(mcontext,LayoutActivity.class);
                        mcontext.startActivity(intent);
                        break;
                    case R.id.start_UICustomViews:
                        intent = new Intent(mcontext,UICustomViewsActivity.class);
                        mcontext.startActivity(intent);
                        break;
                    case R.id.start_ListView:
                        intent = new Intent(mcontext,ListViewActivity.class);
                        mcontext.startActivity(intent);
                        break;
                    case R.id.start_BroadcastActivity:
                        intent = new Intent(mcontext,BroadcastActivity.class);
                        mcontext.startActivity(intent);
                        break;
                    case R.id.send_broadcast:
                        // Intent(自定义action)
                        intent = new Intent("com.example.myapplication.MY_BROADCAST");
                        // 标准广播
                        mcontext.sendBroadcast(intent);
                        break;
                    case R.id.save_data:
//                        // 通过getSharedPreferences()指定SharedPreferences的文件名为SharedData
//                        SharedPreferences.Editor editor = mcontext.getSharedPreferences("SharedData",MODE_PRIVATE).edit();
//                        editor.putString("name","nizilin");
//                        editor.putInt("age", 26);
//                        editor.putBoolean("married",false);
//                        // apply()方法来提交
//                        editor.apply();
                        break;
                    case R.id.restore_data:
//                        SharedPreferences pref = mcontext.getSharedPreferences("SharedData",MODE_PRIVATE);
//                        String name = pref.getString("name","");
//                        int age = pref.getInt("age",0);
//                        boolean married = pref.getBoolean("married",false);
//                        Toast.makeText(mcontext,"姓名："+name+"-年龄："+age+"-婚姻状况："+married,Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.start_login:
                        intent = new Intent(mcontext,LoginActivity.class);
                        mcontext.startActivity(intent);
                        break;
                    default:
                        break;
                }

            }
        });
        return view;
    }
}
