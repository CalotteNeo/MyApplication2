package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

//    private String[] data = {"林宥嘉","TaylorSwift","鲸鱼马戏团","BrunoMars","卢广仲","布朗尼","MrMiss",
//            "林宥嘉","TaylorSwift","鲸鱼马戏团","BrunoMars","卢广仲","布朗尼","MrMiss",
//            "林宥嘉","TaylorSwift","鲸鱼马戏团","BrunoMars","卢广仲","布朗尼","MrMiss"};

    private List<Musician> musiciansList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
//        //ArrayAdapter<适配类型>
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(ListViewActivity.this, android.R.layout.simple_list_item_1, data);
//        ListView listView = (ListView) findViewById(R.id.list_view);
//        listView.setAdapter(adapter);
        initMusicians();//初始化子项目数据
        MusicianAdapter adapter = new MusicianAdapter(ListViewActivity.this, R.layout.musician_item,musiciansList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        /*
        给item设置点击事件
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Musician musician = musiciansList.get(position);
                Toast.makeText(ListViewActivity.this,musician.getName(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    /*
    可以优化运行效率
     */
    private void initMusicians() {
        for (int i = 0; i<2; i++){
            Musician yoga = new Musician("林宥嘉",R.drawable.linyoujia);
            Musician taylor = new Musician("TaylorSwift",R.drawable.taylor);
            Musician whalecircus = new Musician("鲸鱼马戏团",R.drawable.whalecircus);
            Musician BrunoMars = new Musician("BrunoMars",R.drawable.bruno);
            Musician luguangzhong = new Musician("卢广仲",R.drawable.luguangzhong);
            Musician brownie = new Musician("布朗尼乐队",R.drawable.brownie);
            Musician MrMiss = new Musician("MrMiss",R.drawable.mrmiss);
            musiciansList.add(yoga);
            musiciansList.add(taylor);
            musiciansList.add(whalecircus);
            musiciansList.add(BrunoMars);
            musiciansList.add(luguangzhong);
            musiciansList.add(brownie);
            musiciansList.add(MrMiss);

        }
    }
}