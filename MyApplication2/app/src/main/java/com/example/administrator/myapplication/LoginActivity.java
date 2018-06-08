package com.example.administrator.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.UserDataManage.UserData;
import com.example.administrator.myapplication.UserDataManage.UserDataOperator;

/**
 * LoginActivity：用户登录界面数据处理
 */
public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private EditText edt_name,edt_psw;
    private TextView register;
    private CheckBox remanberPsw;
    private SharedPreferences login_sp;  //SharedPreferences保存数据
    private UserDataOperator  userOperator;        //用户数据管理类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        login_onClick();
    }

    //初始化控件
    public void  init(){
        btn_login = (Button)findViewById(R.id.btn_login);
        edt_name = (EditText)findViewById(R.id.edt_names);
        edt_psw = (EditText)findViewById(R.id.edt_psw);
        remanberPsw = (CheckBox)findViewById(R.id.login_rememberpassword2);
        register = (TextView) findViewById(R.id.tet_register);


        //获取保存在userInfo中的数据
        login_sp = getSharedPreferences("userInfo", 0);
        String name=login_sp.getString("USER_NAME", "");
        String pwd =login_sp.getString("PASSWORD", "");
        boolean choseRemember =login_sp.getBoolean("mRememberCheck", false);
        edt_name.setText(name);
        //如果上次选了记住密码，那进入登录页面也自动勾选记住密码，并填上用户名和密码
        if(choseRemember){
            edt_name.setText(name);
            edt_psw.setText(pwd);
            remanberPsw.setChecked(true);
        }

        userOperator = new UserDataOperator(this);

    }
    //按钮点击事件
    public void  login_onClick(){
        //登录按钮点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //登录事件处理
                login();
            }
        });

        //注册点击事件
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //登录事件
    public void login() {
        //获取输入框的用户名和密码
        String userName =edt_name.getText().toString().trim();
        String userPwd = edt_psw.getText().toString().trim();
        if (userName.isEmpty()) {
            Toast.makeText(this, "用户名不能为空",
                    Toast.LENGTH_SHORT).show();

        } else if (userPwd.isEmpty()) {
            Toast.makeText(this, "密码不能为空",
                    Toast.LENGTH_SHORT).show();
        }else {
            SharedPreferences.Editor editor =login_sp.edit();
            boolean result=userOperator.findUserByNameAndPwd(userName, userPwd);
           if(result){                                             //返回true说明用户名和密码均正确

               // 将用户名，密码，校园卡状态,班级，学号查询出来，保存到SharedPreferences
               UserData user = userOperator.accountInquiry(userName);
               editor.putString("USER_NAME", userName);
               editor.putString("PASSWORD", userPwd);
               editor.putString("card_state", user.getcard_state());
               editor.putString("id", String.valueOf(user.getUserId()));
               editor.putString("className", user.getClassName());

                //是否记住密码
                if(remanberPsw.isChecked()){
                    editor.putBoolean("mRememberCheck", true);
                }else{
                    editor.putBoolean("mRememberCheck", false);
                }
                editor.commit();

                Intent intent = new Intent(LoginActivity.this,FunctionActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(this,"登录成功！欢迎"+userName+"!",Toast.LENGTH_SHORT).show();//登录成功提示
            }else {
                Toast.makeText(this, "登录失败，请检查用户名或密码！",Toast.LENGTH_SHORT).show();  //登录失败提示
            }
        }
    }


}
