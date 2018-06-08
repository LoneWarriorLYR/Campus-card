package com.example.administrator.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.UserDataManage.UserData;
import com.example.administrator.myapplication.UserDataManage.UserDataOperator;

public class RechargeActivity extends AppCompatActivity {

    private String name; //用户名
    private EditText edt_name,edt_balace;
    private Button btn_update;
    private SharedPreferences login_sp;  //SharedPreferences保存数据
    UserDataOperator UserDataOperator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        init();
    }


    //初始化控件
    private void init() {
        edt_name = (EditText) findViewById(R.id.edt_Uname);
        edt_balace = (EditText) findViewById(R.id.edt_balance);
        btn_update = (Button)findViewById(R.id.btn_updateBalance);
        //打开数据库
        UserDataOperator = new UserDataOperator(this);
        //获取保存在userInfo中的数据
        login_sp = getSharedPreferences("userInfo", 0);
        name=login_sp.getString("USER_NAME", "");
        edt_name.setText("用户名："+name);
        edt_name.setEnabled(false);


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //校园卡充值
                recharge();
            }
        });

    }

    //校园卡充值
    private void recharge() {
        //首先获取用户的卡余额
        //查询余额
        UserData user = UserDataOperator.accountInquiry(name);
        int balance = user.getBalance();
        //获取卡片状态
        String card_state = user.getcard_state();
        //获取要充值的金额
        String num = edt_balace.getText().toString().trim();

        Toast.makeText(RechargeActivity.this, "校园卡的状态:"+card_state, Toast.LENGTH_SHORT).show();
        Toast.makeText(RechargeActivity.this, "校园卡余额:"+balance, Toast.LENGTH_SHORT).show();
       //首先判断输入内容是否为空
       if (num.equals("")||num .equals(null)){
              Toast.makeText(RechargeActivity.this, "充值金额不能为空！", Toast.LENGTH_SHORT).show();
        }else {
          int number = Integer.parseInt(num);
           //如果校园卡状态为挂失中，不可充值
           if (card_state.equals("挂失中")) {
               Toast.makeText(RechargeActivity.this, "校园卡的状态为挂失中，请解除挂失后，再充值！", Toast.LENGTH_SHORT).show();
           } else if(number>200){//单次充值金额不可以大于200元
            edt_balace.setText("");
            Toast.makeText(RechargeActivity.this, "单次充值金额不可以大于200元！", Toast.LENGTH_SHORT).show();
           } else {//校园卡正常可充值
               //获取要充值的金额
               number += balance;
               //进行充值
               boolean isOk = UserDataOperator.recharge(number, name);
               //充值成功，给出提示信息
               if (isOk) {
                   Toast.makeText(RechargeActivity.this, "嘻嘻！充值成功，您现在的余额为：" + number + "元", Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(RechargeActivity.this, "很遗憾充值失败，出现未知错误，请联系网管！", Toast.LENGTH_SHORT).show();
               }
           }
       }

    }

}
