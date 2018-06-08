package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication.UserDataManage.UserData;
import com.example.administrator.myapplication.UserDataManage.UserDataOperator;

public class AccountInquiryActivity extends AppCompatActivity {

    private TextView txt_uid; //学号
    private TextView txt_name; //姓名
    private TextView txt_className; //班级
    private TextView txt_balance; //余额
    private TextView txt_card_state; // 校园卡状态
    private SharedPreferences login_sp;  //SharedPreferences保存数据
    UserDataOperator UserDataOperator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_inquiry);

        //初始化
        init();

    }

    //初始化控件
    private void init() {
        txt_uid = (TextView)findViewById(R.id.txt_uid);
        txt_name = (TextView)findViewById(R.id.txt_name);
        txt_className = (TextView)findViewById(R.id.txt_className);
        txt_balance = (TextView)findViewById(R.id.txt_balance);
        txt_card_state = (TextView)findViewById(R.id.txt_card_state);
        //打开数据库
        UserDataOperator = new UserDataOperator(this);
        //获取用户信息
        accountInquiry();
    }
    //获取用户信息
    private void  accountInquiry(){
        //获取保存在userInfo中的数据
        login_sp = getSharedPreferences("userInfo", 0);
        String name=login_sp.getString("USER_NAME", "");
        txt_name.setText(name);
        UserData user = UserDataOperator.accountInquiry(name);

        //获取信息
        String  id = Integer.toString(user.getUserId());
        String userName = user.getUserName();
        String className= user.getClassName();
        String balance  =Integer.toString( user.getBalance());
        String card_state = user.getcard_state();

        //显示信息
        txt_uid.setText(id);
        txt_name.setText(userName);
        txt_className.setText(className);
        txt_balance.setText(balance);
        txt_card_state.setText(card_state);

    }

}
