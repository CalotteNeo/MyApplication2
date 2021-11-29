package com.example.myapplication;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 2.6 用一个专门的集合类来管理所有的活动。
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();
    // 2.6 向list中添加活动
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    // 2.6 从List中移除活动
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    // 2.6 将List中存储的活动全部销毁
    public static void finishAll(){
        for (Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        //List.clear()方法：从列表中移除所有元素
        activities.clear();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
