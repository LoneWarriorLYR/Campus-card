package com.example.administrator.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.administrator.myapplication.UserDataManage.UserDataOperator;

public class LockActivity extends AppCompatActivity {

    private Switch lock;
    private ImageView img;
    private UserDataOperator UserDataOperator;
    private SharedPreferences login_sp;  //SharedPreferences保存数据
    private String name;//用户名
    private String card_state;//校园卡状态

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        init();
    }

    //初始化控件
    private  void  init(){
        lock = (Switch)findViewById(R.id.swith_lock);
        img = (ImageView)findViewById(R.id.img_lock);
        //打开数据库
        UserDataOperator = new UserDataOperator(this);
        //获取保存在userInfo中的数据
        login_sp = getSharedPreferences("userInfo", 0);
        name=login_sp.getString("USER_NAME", "");
        card_state = login_sp.getString("card_state","");
        //判断校园卡状态是否是挂失状态
        if(card_state.equals("挂失中")){
            lock.setEnabled(false);
            Toast.makeText(LockActivity.this,"校园卡状态已经是挂失状态,不可以重复挂失哦！！！",Toast.LENGTH_SHORT).show();
        }else{
            //挂失
            change();
        }
    }

    //switch事件
    private void change(){

       lock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
               if(isChecked){
                   //选中
                   img.setImageDrawable(getResources().getDrawable(R.drawable.lock));
                   //改变校园卡状态：挂失中
                   UserDataOperator.lock(name);
                   //改变获取保存在userInfo中的校园卡状态
                   SharedPreferences.Editor editor =login_sp.edit();
                   editor.putString("card_state", "挂失中");
                   editor.commit();
                   Toast.makeText(LockActivity.this,"挂失成功！",Toast.LENGTH_SHORT).show();
               }

           }
       });

    }

}
