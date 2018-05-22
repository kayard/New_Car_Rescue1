package com.kuaijie.new_car_rescue.MyApplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;

import java.util.LinkedList;
import java.util.List;

/**
 * desc:全局初始化操作
 * Created by kathe on 2018/4/14.
 */

public class App extends Application {

    public static List<Activity> activities = new LinkedList<>();

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
        OkGo.getInstance()
                .init(this);
    }

    public static Context getmContext() {
        return mContext;
    }

    /**
     * 退出程序
     */
    public static void exit() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }

}
