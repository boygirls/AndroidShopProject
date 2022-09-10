package com.fxc.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.fxc.R;
import com.fxc.main_tab_activity;

import java.util.Timer;
import java.util.TimerTask;

public class Welcome extends Activity {



    private static final long DELAY = 2000;
    private TimerTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // 两秒后进入主页面
        final Intent localIntent=new Intent(this, main_tab_activity.class);//你要转向的Activity
        Timer timer=new Timer();
        TimerTask tast=new TimerTask() {
            @Override
            public void run(){
                startActivity(localIntent);//执行
            }
        };
        timer.schedule(tast,DELAY); //2秒后
    }
}