package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGHT = 2500;  //延迟2.5秒
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //隐藏标题栏以及状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //欢迎界面
        //Toast.makeText(this,"欢迎使用广职院校园一卡通",Toast.LENGTH_SHORT).show();
        //延迟2.5秒后进入登录界面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }

}
