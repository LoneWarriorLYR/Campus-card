package com.example.administrator.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.chart.MainActivity;

public class FunctionActivity extends AppCompatActivity {

    private Button btn_account,btn_lock,btn_unlock,btn_chongzhi,
            btn_xiaofei,btn_updateInfo,btn_edtPsw,btn_school,btn_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);

        //初始化控件
        init();
    }

    //初始化控件
    private void init(){
        btn_account = (Button)findViewById(R.id.btn_account);
        btn_lock = (Button)findViewById(R.id.btn_lock);
        btn_unlock = (Button)findViewById(R.id.btn_unlock);
        btn_chongzhi = (Button)findViewById(R.id.btn_chongzhi);
        btn_xiaofei = (Button)findViewById(R.id.btn_xiaofei);
        btn_updateInfo = (Button)findViewById(R.id.btn_updateInfo);
        btn_edtPsw = (Button)findViewById(R.id.btn_edtPsw);
        btn_school = (Button)findViewById(R.id.btn_school);
        btn_setting = (Button)findViewById(R.id.btn_setting);

        //绑定按钮点击事件
        btn_onClick();
    }

    //绑定按钮点击事件
    private void btn_onClick(){

        //账号查询
        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FunctionActivity.this,AccountInquiryActivity.class);
                startActivity(intent);

            }
        });

        //卡片挂失
        btn_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FunctionActivity.this,LockActivity.class);
                startActivity(intent);
            }
        });

        //卡片解挂
        btn_unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FunctionActivity.this,UnLockActivity.class);
                startActivity(intent);
            }
        });

        //卡片充值
        btn_chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FunctionActivity.this,RechargeActivity.class);
                startActivity(intent);
            }
        });

        //消费记录
        btn_xiaofei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FunctionActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //修改个人信息
        btn_updateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FunctionActivity.this,UpdateInfoActivity.class);
                startActivity(intent);
            }
        });

        //校园中心
        btn_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("http://www.gdptc.cn/default.shtml");  //url为你要链接的地址
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                /*Intent intent = new Intent(FunctionActivity.this,SchoolWebActivity.class);
                startActivity(intent);*/
            }
        });

        //修改密码
        btn_edtPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FunctionActivity.this,UpdatePswActivity.class);
                startActivity(intent);
            }
        });

        //设置中心
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FunctionActivity.this,SetingActivity.class);
                startActivity(intent);
            }
        });

    }
}
