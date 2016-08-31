package com.befiring.easypicture.app;

import android.app.Application;

/**
 * Created by Administrator on 2016/8/31.
 */
public class EasyPictureApplication extends Application{

    private static EasyPictureApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
    public static EasyPictureApplication getInstance(){
        return instance;
    }
}
