package cn.zybwz.quickui;

import android.app.Application;

import cn.zybwz.components.Utils;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.application=this;
    }
}
