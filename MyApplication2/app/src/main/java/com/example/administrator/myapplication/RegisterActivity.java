package com.example.administrator.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.UserDataManage.UserData;
import com.example.administrator.myapplication.UserDataManage.UserDataOperator;

import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    private EditText mAccount;                        //用户名编辑
    private EditText mPwd;                            //密码编辑
    private EditText mPwdCheck;                       //密码编辑
    private Button mRegister;                       //注册按钮
    private UserDataOperator userDataOperator;         //用户数据管理类
    private SharedPreferences login_sp;  //SharedPreferences保存数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }
    //初始化控件
    public  void init(){
        mAccount = (EditText) findViewById(R.id.edt_username);
        mPwd = (EditText) findViewById(R.id.edt_psw);
        mPwdCheck = (EditText) findViewById(R.id.edt_psw2);
        mRegister = (Button) findViewById(R.id.btn_lock);

        //建立数据库连接
        userDataOperator = new UserDataOperator(this);
        //执行注册按键点击事件
        register_onClick();
    }

    //注册按键点击事件
    public void register_onClick(){

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //注册事件处理
                try {
                    register();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void register() throws InterruptedException {
        //获取输入框的用户名和密码
        String userName = mAccount.getText().toString().trim();
        String userPwd = mPwd.getText().toString().trim();
        String userPwd2 = mPwdCheck.getText().toString().trim();
        Toast.makeText(this, "开始ing",Toast.LENGTH_SHORT).show();
        //验证输入内容是否为空
        if (userName.equals("")) {
            Toast.makeText(this, "用户名不能为空",
                    Toast.LENGTH_SHORT).show();
        } else if (userPwd.equals("")) {
            Toast.makeText(this, "密码不能为空",
                    Toast.LENGTH_SHORT).show();
        } else if (userPwd2.equals("")) {
            Toast.makeText(this, "密码不能为空",
                    Toast.LENGTH_SHORT).show();
        } else if(!userPwd.equals(userPwd2)){
            Toast.makeText(this, "两次输入的密码不一致！",
                    Toast.LENGTH_SHORT).show();
        }else{
            //检查用户是否存在
            boolean isExit = userDataOperator.findUserByName(userName);
            //用户已经存在时返回，给出提示文字
            if(isExit){
                Toast.makeText(this, "用户名"+ userName+"已经被注册使用，请使用其他用户名",Toast.LENGTH_SHORT).show();
                return ;
            }else {
                //将输入的用户数据保存到新建的对象
                Random r = new Random();
                int  id = r.nextInt(201500);
                String  className = "物联网15";
                int  balance = 100;
                String  card_state = "正常";

                //将输入的用户数据保存到新建的对象
                UserData mUser = new UserData(id,userName, userPwd,className,balance,card_state);
                //mUserDataManager.openDataBase();
                //新建用户信息
                boolean flag = userDataOperator.add(mUser);
                if (!flag) {
                    Toast.makeText(this, "Sorry，注册用户失败！",Toast.LENGTH_SHORT).show();
                }else{
                    //将用户名保存在userInfo中的数据中
                    login_sp = getSharedPreferences("userInfo", 0);
                    SharedPreferences.Editor editor =login_sp.edit();
                    editor.putString("USER_NAME", userName);
                    editor.commit();
                    Toast.makeText(this, "注册用户成功，即将跳转到登录界面",Toast.LENGTH_SHORT).show();
                    Thread.sleep(1002);
                    Intent intent_Register_to_Login = new Intent(RegisterActivity.this,LoginActivity.class) ;    //切换User Activity至Login Activity
                    startActivity(intent_Register_to_Login);
                    finish();
                }
            }
        }


    }

}
