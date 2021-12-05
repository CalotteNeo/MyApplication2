package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * 自定义的适配器，这个适配器继承自ArrayAdapter，并将泛型指定为Musician类
 */
public class MusicianAdapter extends ArrayAdapter<Musician> {
    private int resourceId;
    public MusicianAdapter (Context context, int textViewResourceId, List<Musician> objects){
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
        Musician musician = getItem(position);//获取当前的musician实例
        // LayoutInflater这个方法，不是很懂
        // 不是很懂↓这个写法，但这是ListView中的标准写法，先这样吧。
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView musicianImage = (ImageView) view.findViewById(R.id.musician_image);
        TextView musicianName = (TextView) view.findViewById(R.id.musician_name);
        musicianImage.setImageResource(musician.getImageId());
        musicianName.setText(musician.getName());
        return view;
    }
}
