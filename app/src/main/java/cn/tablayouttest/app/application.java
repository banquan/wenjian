package cn.tablayouttest.app;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by yu on 2017/10/17.
 */

public class application extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initBomb();
    }

    private void initBomb() {
        Bmob.initialize(this,"cc6b20f0a8ad63b7a930cb97bdffd204");
    }
}
