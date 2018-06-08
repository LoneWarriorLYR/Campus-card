package com.example.administrator.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.UserDataManage.UserDataOperator;

public class UpdatePswActivity extends AppCompatActivity {

    private String name; //用户名
    private EditText edt_name,edt_newPsw;
    private Button btn_update;
    private SharedPreferences login_sp;  //SharedPreferences保存数据
    UserDataOperator UserDataOperator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_psw);
        init();
    }

    //初始化控件
    private void init() {
        edt_name = (EditText) findViewById(R.id.edt_names);
        edt_newPsw = (EditText) findViewById(R.id.edt_newPsw);
        btn_update = (Button)findViewById(R.id.btn_update);
        //打开数据库
        UserDataOperator = new UserDataOperator(this);
        //获取保存在userInfo中的数据
        login_sp = getSharedPreferences("userInfo", 0);
        name=login_sp.getString("USER_NAME", "");
        edt_name.setText("用户名："+name);
        edt_name.setEnabled(false);
         //修改密码
        updatePsw();
    }

    private void updatePsw() {

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入内容
                String newPsw = edt_newPsw.getText().toString().trim();
                //输入密码为空
                if(newPsw.equals("")||newPsw.equals(null)){
                    Toast.makeText(UpdatePswActivity.this,"新密码不能为空！"+newPsw,Toast.LENGTH_SHORT).show();//修改成功提示
                }else {
                    UserDataOperator.update(newPsw, name);
                    Toast.makeText(UpdatePswActivity.this, "嘻嘻！修改密码成功，请重新登录哦", Toast.LENGTH_SHORT).show();//修改成功提示
                    try {
                        Thread.sleep(1236);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(UpdatePswActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
